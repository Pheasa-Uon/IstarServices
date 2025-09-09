package com.istar.corebanking.service.administrator.usersmanagement.permission;

import com.istar.corebanking.dto.administrator.usersmanagement.permission.MenuPermissionDTO;
import com.istar.corebanking.entity.administrator.feature.Menu;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleMenuPermission;
import com.istar.corebanking.repository.administrator.feature.MenuRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleMenuPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuPermissionService {

    private final MenuRepository mainMenuRepo;
    private final RoleMenuPermissionRepository roleMenuRepo;

    // 1. For admin => return all menus visible
    public Map<String, MenuPermissionDTO> getAllMenuPermissions() {
        List<Menu> allMenus = mainMenuRepo.findAll();

        return allMenus.stream()
                .collect(Collectors.toMap(
                        Menu::getCode,
                        menu -> {
                            MenuPermissionDTO dto = new MenuPermissionDTO();
                            dto.setCode(menu.getCode());
                            dto.setLabel(menu.getName());
                            dto.setRoute(menu.getRoutePath());
                            dto.setIcon(menu.getIcon());
                            dto.setVisible(true); // admin sees all
                            return dto;
                        }
                ));
    }

    // 2. For normal user => merge across roles
    public Map<String, MenuPermissionDTO> mergeByMenu(Set<Role> roles) {
        // Collect role IDs
        List<Long> roleIds = roles.stream().map(Role::getId).toList();

        // Fetch menu permissions for those roles
        List<RoleMenuPermission> roleMenuPermissions = roleMenuRepo.findByRoleIdIn(roleIds);

        // Merge by menu code
        Map<String, MenuPermissionDTO> merged = new HashMap<>();

        for (RoleMenuPermission perm : roleMenuPermissions) {
            Menu menu = perm.getMainMenu();
            String code = menu.getCode();

            merged.compute(code, (k, existing) -> {
                if (existing == null) {
                    existing = new MenuPermissionDTO();
                    existing.setCode(menu.getCode());
                    existing.setLabel(menu.getName());
                    existing.setRoute(menu.getRoutePath());
                    existing.setIcon(menu.getIcon());
                    existing.setVisible(Boolean.TRUE.equals(perm.getIsVisible()));
                } else {
                    // If any role allows visibility, keep it visible
                    existing.setVisible(existing.isVisible() || Boolean.TRUE.equals(perm.getIsVisible()));
                }
                return existing;
            });
        }

        return merged;
    }
}
