package com.istar.service.service.administrator.usersmanagement.user;

import com.istar.service.entity.administrator.usersmanagement.permission.Role;
import com.istar.service.entity.administrator.usersmanagement.user.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole assignRoleToUser(Long userId, Long roleId);
    void removeRoleFromUser(Long userId, Long roleId);
    List<Role> getUserRoles(Long userId);

}
