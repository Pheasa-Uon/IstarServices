package com.istar.corebanking.service.authentication;

import com.istar.corebanking.entity.administrator.feature.Feature;
import com.istar.corebanking.entity.administrator.feature.MainMenu;
import com.istar.corebanking.entity.administrator.feature.Reports;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleMainMenuPermission;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleReportPermission;
import com.istar.corebanking.repository.administrator.feature.FeatureRepository;
import com.istar.corebanking.repository.administrator.feature.MainMenuRepository;
import com.istar.corebanking.repository.administrator.feature.ReportRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleFeaturePermissionRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleMainMenuPermissionRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleReportPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final RoleFeaturePermissionRepository rfpRepo;
    private final RoleMainMenuPermissionRepository rmRepo;
    private final RoleReportPermissionRepository rrRepo;

    private final FeatureRepository featureRepository;
    private final MainMenuRepository mainMenuRepository;
    private final ReportRepository reportRepository;

    // =========== FEATURE PERMISSION ===========

    // Return merged permissions for a user's roles
    public Map<String, FeaturePermissionFlags> mergeByFeature(Set<Role> roles) {
        Map<String, FeaturePermissionFlags> map = new HashMap<>();

        // Convert role set to a list of IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleFeaturePermission entries
        for (RoleFeaturePermission rfp : rfpRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            String feature = rfp.getFeature().getCode();

            FeaturePermissionFlags flags = new FeaturePermissionFlags(
                    rfp.getIsSearch(),
                    rfp.getIsAdd(),
                    rfp.getIsViewed(),
                    rfp.getIsEdit(),
                    rfp.getIsApprove(),
                    rfp.getIsReject(),
                    rfp.getIsDeleted(),
                    rfp.getIsSave(),
                    rfp.getIsClear(),
                    rfp.getIsCancel(),
                    rfp.getIsProcess(),
                    rfp.getIsImport(),
                    rfp.getIsExport()
            );

            // Merge permissions for the same feature
            map.merge(feature, flags, FeaturePermissionFlags::or);
        }
        return map;
    }

    // Return all permissions (full access for admin)
    public Map<String, FeaturePermissionFlags> getAllFeaturePermissions() {
        Map<String, FeaturePermissionFlags> all = new HashMap<>();

        // Fetch all features from DB
        List<Feature> features = featureRepository.findAll();

        for (Feature feature : features) {
            // Here we read flags from Feature entity instead of hardcoding true
            all.put(feature.getCode(), new FeaturePermissionFlags(
                    feature.isSearch(),
                    feature.isAdd(),
                    feature.isViewed(),
                    feature.isEdit(),
                    feature.isApprove(),
                    feature.isReject(),
                    feature.isDeleted(),
                    feature.isSave(),
                    feature.isClear(),
                    feature.isCancel(),
                    feature.isProcess(),
                    feature.isImport(),
                    feature.isExport()
            ));
        }

        return all;
    }

    // =========== MAIN MENU PERMISSION ===========

    // Return merged menu permission for a user's role
    public Map<Long, Boolean> mergeMenuPermissions(Set<Role> roles) {
        Map<Long, Boolean> map = new HashMap<>();

        // Convert role set to a list IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleMainMenuPermission entries
        for (RoleMainMenuPermission rm : rmRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            Long mainMenuId = rm.getMainMenu().getId();

            map.merge(mainMenuId, rm.getIsVisible(),(existing, newValue) -> existing && newValue);
        }
        return map;
    }

    // Return all menu permission (visible for admin)
    public Map<Long, Boolean> getAllMenuPermissions() {
        Map<Long, Boolean> all = new HashMap<>();

        // Fetch all menu permission from DB and set them as visible for admin
        List<MainMenu> mainMenus = mainMenuRepository.findAll();
        for (MainMenu mainMenu : mainMenus) {
            all.put(mainMenu.getId(), true);
        }
        return all;
    }

    // Get visible menu IDs for a user
    public Set<Long> getVisibleMenuIds(Set<Role> roles) {
        Map<Long, Boolean> menuPermissions = mergeMenuPermissions(roles);
        return menuPermissions.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // =========== REPORT PERMISSIONS ===========

    // Return merged report permissions for a user's roles
    public Map<Long, ReportPermissionFlags> mergeReportPermissions(Set<Role> roles) {
        Map<Long, ReportPermissionFlags> map = new HashMap<>();

        // Convert role set to a list of IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleReportPermission entries
        for (RoleReportPermission rrp : rrRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            Long reportId = rrp.getReport().getId();

            ReportPermissionFlags flags = new ReportPermissionFlags(
                    rrp.getIsViewed(),
                    rrp.getIsExport()
            );
            // Merge permissions for the same report using Or Logic
            map.merge(reportId, flags, ReportPermissionFlags::or);
        }
        return map;
    }

    // Return all report permissions (full access for admin)
    public Map<Long, ReportPermissionFlags> getAllReportPermissions() {
        Map<Long, ReportPermissionFlags> all = new HashMap<>();

        // Fetch all reports from DB
        List<Reports> reports = reportRepository.findAll();

        for (Reports report : reports) {
            // Admin has full access to all reports
            all.put(report.getId(), new ReportPermissionFlags(true,true));
        }
        return all;
    }

    // Get accessible report IDs for a user (can view or export)
    public Set<Long> getAccessibleReportIds(Set<Role> roles) {
        Map<Long, ReportPermissionFlags> reportPermissions = mergeReportPermissions(roles);
        return reportPermissions.entrySet().stream()
                .filter(entry -> entry.getValue().canView() || entry.getValue().canExport())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // === AUTHORITIES GENERATION ===

    public List<String> toAuthorities(Map<String, FeaturePermissionFlags> map) {
        List<String> authorities = new ArrayList<>();
        map.forEach((feature, flags) -> {
            if (flags.search()) authorities.add("FEATURE" + feature + ".search");
            if (flags.add()) authorities.add("FEATURE" + feature + ".add");
            if (flags.view()) authorities.add("FEATURE" + feature + ".view");
            if (flags.edit()) authorities.add("FEATURE" + feature + ".edit");
            if (flags.approve()) authorities.add("FEATURE" + feature + ".approve");
            if (flags.reject()) authorities.add("FEATURE" + feature + ".reject");
            if (flags.deleted()) authorities.add("FEATURE" + feature + ".deleted");
            if (flags.save()) authorities.add("FEATURE" + feature + ".save");
            if (flags.clear()) authorities.add("FEATURE" + feature + ".clear");
            if (flags.cancel()) authorities.add("FEATURE" + feature + ".cancel");
            if (flags.process()) authorities.add("FEATURE" + feature + ".process");
            if (flags.imported() ) authorities.add("FEATURE" + feature + ".imported");
            if (flags.exported() ) authorities.add("FEATURE" + feature + ".exported");
        });
        return authorities;
    }

    public List<String> toMenuAuthorities(Map<Long, Boolean> menuPermissions) {
        List<String> authorities = new ArrayList<>();
        menuPermissions.forEach((menuId, isVisible) -> {
            if (isVisible) {
                authorities.add("MENU_" + menuId + "_ACCESS");
            }
        });
        return authorities;
    }

    public List<String> toReportAuthorities(Map<Long, ReportPermissionFlags> reportPermissions) {
        List<String> authorities = new ArrayList<>();
        reportPermissions.forEach((reportId, flags) -> {
            if (flags.canView()) authorities.add("REPORT_" + reportId + "_VIEW");
            if (flags.canExport()) authorities.add("REPORT_" + reportId + "_EXPORT");
        });
        return authorities;
    }

    // === COMPLETE AUTHORITIES FOR USER ===

    public List<String> getAllAuthorities(Set<Role> roles) {
        List<String> authorities = new ArrayList<>();

        // Add feature authorities
        authorities.addAll(toAuthorities(mergeByFeature(roles)));

        // Add menu authorities
        authorities.addAll(toMenuAuthorities(mergeMenuPermissions(roles)));

        // Add report authorities
        authorities.addAll(toReportAuthorities(mergeReportPermissions(roles)));

        return authorities;
    }

    // === PERMISSION CHECK METHODS ===

    public boolean hasFeaturePermission(Set<Role> roles, String featureCode, String permissionType) {
        Map<String, FeaturePermissionFlags> featurePermissions = mergeByFeature(roles);
        FeaturePermissionFlags flags = featurePermissions.get(featureCode);

        if (flags == null) return false;

        return switch (permissionType.toUpperCase()) {
            case "SEARCH" -> flags.search();
            case "ADD" -> flags.add();
            case "VIEW" -> flags.view();
            case "EDIT" -> flags.edit();
            case "APPROVE" -> flags.approve();
            case "REJECT" -> flags.reject();
            case "DELETE" -> flags.deleted();
            case "SAVE" -> flags.save();
            case "CLEAR" -> flags.clear();
            case "CANCEL" -> flags.cancel();
            case "PROCESS" -> flags.process();
            case "IMPORT" -> flags.imported();
            case "EXPORT" -> flags.exported();
            default -> false;
        };
    }

    public boolean hasMenuAccess(Set<Role> roles, Long menuId) {
        Map<Long, Boolean> menuPermissions = mergeMenuPermissions(roles);
        return Boolean.TRUE.equals(menuPermissions.get(menuId));
    }

    public boolean hasReportPermission(Set<Role> roles, Long reportId, String permissionType) {
        Map<Long, ReportPermissionFlags> reportPermissions = mergeReportPermissions(roles);
        ReportPermissionFlags flags = reportPermissions.get(reportId);

        if (flags == null) return false;

        return switch (permissionType.toUpperCase()) {
            case "VIEW" -> flags.canView();
            case "EXPORT" -> flags.canExport();
            default -> false;
        };
    }
}
