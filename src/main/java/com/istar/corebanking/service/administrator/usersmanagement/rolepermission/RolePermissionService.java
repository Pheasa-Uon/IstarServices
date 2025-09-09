package com.istar.corebanking.service.administrator.usersmanagement.rolepermission;

import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.RoleFeaturePermissionDTO;
import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.RoleMenuPermissionDTO;
import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.RoleReportPermissionDTO;
import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.RolePermissionsDTO;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;

import java.util.List;

public interface RolePermissionService {

    List<RoleFeaturePermission> getAllPermissions();
    RolePermissionsDTO getPermissionsByRole(Role role);
    RoleFeaturePermission createPermission(RoleFeaturePermission permission);
    RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission permission);
    void deletePermission(Long id);
    void savePermissionsBulk(List<RoleFeaturePermissionDTO> dtos, List<RoleMenuPermissionDTO> mainMenuPermissions, List<RoleReportPermissionDTO> reportPermission);
}
