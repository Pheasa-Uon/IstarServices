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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainMenuPermissionService {

    private final RoleFeaturePermissionRepository rfpRepo;
    private final RoleMainMenuPermissionRepository rmRepo;
    private final RoleReportPermissionRepository rrRepo;

    private final FeatureRepository featureRepository;
    private final MainMenuRepository mainMenuRepository;
    private final ReportRepository reportRepository;

    // =========== MAIN MENU PERMISSION ===========

    // Return merged menu permission for a user's role
    public Map<String, MenuPermissionFlags> mergeByMenu(Set<Role> roles) {
        Map<String, MenuPermissionFlags> map = new HashMap<>();

        // Convert role set to a list of IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleFeaturePermission entries
        for (RoleMainMenuPermission rmp : rmRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            String menu = rmp.getMainMenu().getCode();

            MenuPermissionFlags flags = new MenuPermissionFlags(
                    rmp.getIsVisible()
            );

            // Merge permissions for the same feature
            map.merge(menu, flags, MenuPermissionFlags::or);
        }
        return map;
    }

    // Return all permissions (full access for admin)
    public Map<String, MenuPermissionFlags> getAllMenuPermissions() {
        Map<String, MenuPermissionFlags> all = new HashMap<>();

        // Fetch all features from DB
        List<MainMenu> features = mainMenuRepository.findAll();

        for (MainMenu mainMenu : features) {
            // Here we read flags from Feature entity instead of hardcoding true
            all.put(mainMenu.getCode(), new MenuPermissionFlags(
                    mainMenu.getIsVisible()
            ));
        }

        return all;
    }

}
