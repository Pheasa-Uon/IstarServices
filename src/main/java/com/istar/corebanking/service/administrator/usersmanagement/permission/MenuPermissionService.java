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

    // Build DTO with children recursively
    private MenuPermissionDTO buildMenuDTO(Menu menu, boolean visible) {
        MenuPermissionDTO dto = new MenuPermissionDTO();
        dto.setCode(menu.getCode());
        dto.setLabel(menu.getName());
        dto.setRoute(menu.getRoutePath());
        dto.setIcon(menu.getIcon());
        dto.setVisible(visible);

        List<MenuPermissionDTO> childDtos = menu.getChildren().stream()
                .map(child -> buildMenuDTO(child, visible))
                .collect(Collectors.toList());

        dto.setChildren(childDtos);
        return dto;
    }

    // Admin: return all menus
    public List<MenuPermissionDTO> getAllMenuPermissions() {
        List<Menu> allMenus = mainMenuRepo.findAll();

        // Only top-level menus, build recursively
        return allMenus.stream()
                .filter(menu -> menu.getParent() == null) // only root menus
                .map(menu -> buildMenuDTO(menu, true))
                .collect(Collectors.toList());
    }

    // Normal user: merge permissions by roles
    public List<MenuPermissionDTO> mergeByMenu(Set<Role> roles) {
        List<Long> roleIds = roles.stream().map(Role::getId).toList();

        // fetch role-permission entries
        List<RoleMenuPermission> roleMenuPermissions = roleMenuRepo.findByRoleIdIn(roleIds);

        // Map<MenuCode, Visibility>
        Map<String, Boolean> visibilityMap = new HashMap<>();
        for (RoleMenuPermission perm : roleMenuPermissions) {
            String code = perm.getMainMenu().getCode();
            visibilityMap.merge(code, Boolean.TRUE.equals(perm.getIsVisible()), (a, b) -> a || b);
        }

        // fetch all menus to build tree
        List<Menu> allMenus = mainMenuRepo.findAll();

        // build only root menus, propagate children
        return allMenus.stream()
                .filter(menu -> menu.getParent() == null)
                .map(menu -> buildUserMenuDTO(menu, visibilityMap))
                .collect(Collectors.toList());
    }

    // Recursive build for normal users
    private MenuPermissionDTO buildUserMenuDTO(Menu menu, Map<String, Boolean> visibilityMap) {
        MenuPermissionDTO dto = new MenuPermissionDTO();
        dto.setCode(menu.getCode());
        dto.setLabel(menu.getName());
        dto.setRoute(menu.getRoutePath());
        dto.setIcon(menu.getIcon());
        dto.setVisible(visibilityMap.getOrDefault(menu.getCode(), false));

        List<MenuPermissionDTO> childDtos = menu.getChildren().stream()
                .map(child -> buildUserMenuDTO(child, visibilityMap))
                .collect(Collectors.toList());

        dto.setChildren(childDtos);
        return dto;
    }
}
