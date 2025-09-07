package com.istar.corebanking.service.administrator.usersmanagement.permission;

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
import com.istar.corebanking.service.administrator.usersmanagement.permission.record.FeaturePermissionFlags;
import com.istar.corebanking.service.administrator.usersmanagement.permission.record.MenuPermissionFlags;
import com.istar.corebanking.service.administrator.usersmanagement.permission.record.ReportPermissionFlags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeaturePermissionService {

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
}
