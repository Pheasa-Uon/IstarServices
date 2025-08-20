package com.istar.service.service.administrator.usersmanagement.permission;


import com.istar.service.entity.administrator.usersmanagement.permission.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(Long id, Role updated);
    void deleteRole(Long id);
    List<Role> searchRoles(String keyword);

}


