package com.istar.service.service.administrator.usersmanagement.rolepermission;

import com.istar.service.dto.administrator.usersmanagement.rolepermission.FeaturePermissionDTO;
import com.istar.service.dto.administrator.usersmanagement.rolepermission.MainMenuPermissionDTO;
import com.istar.service.dto.administrator.usersmanagement.rolepermission.RolePermissionsDTO;
import com.istar.service.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.service.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;

import java.util.List;

public interface RolePermissionService {

    List<RoleFeaturePermission> getAllPermissions();
    RolePermissionsDTO getPermissionsByRole(Role role);
    RoleFeaturePermission createPermission(RoleFeaturePermission permission);
    RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission permission);
    void deletePermission(Long id);
    void savePermissionsBulk(List<FeaturePermissionDTO> dtos, List<MainMenuPermissionDTO> mainMenuPermissions);
}
