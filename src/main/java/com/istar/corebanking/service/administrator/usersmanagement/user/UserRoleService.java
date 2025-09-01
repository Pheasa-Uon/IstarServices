package com.istar.corebanking.service.administrator.usersmanagement.user;

import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.user.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole assignRoleToUser(Long userId, Long roleId);
    void removeRoleFromUser(Long userId, Long roleId);
    List<Role> getUserRoles(Long userId);

}
