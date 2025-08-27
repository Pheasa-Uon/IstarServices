package com.istar.service.service.administrator.usersmanagement.permission;

import com.istar.service.dto.administrator.usersmanagement.permission.FeaturePermissionDTO;
import com.istar.service.dto.administrator.usersmanagement.permission.MainMenuPermissionDTO;
import com.istar.service.entity.administrator.usersmanagement.permission.Role;
import com.istar.service.entity.administrator.usersmanagement.permission.RoleFeaturePermission;

import java.util.List;

public interface RolePermissionService {

    List<RoleFeaturePermission> getAllPermissions();
    List<FeaturePermissionDTO> getPermissionsByRole(Role role);
    RoleFeaturePermission createPermission(RoleFeaturePermission permission);
    RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission permission);
    void deletePermission(Long id);
    void savePermissionsBulk(List<FeaturePermissionDTO> dtos, List<MainMenuPermissionDTO> mainMenuPermissions);
}
