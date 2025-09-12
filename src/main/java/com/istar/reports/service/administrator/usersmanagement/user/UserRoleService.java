package com.istar.reports.service.administrator.usersmanagement.user;

import com.istar.reports.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.reports.entity.administrator.usersmanagement.user.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole assignRoleToUser(Long userId, Long roleId);
    void removeRoleFromUser(Long userId, Long roleId);
    List<Role> getUserRoles(Long userId);

}
