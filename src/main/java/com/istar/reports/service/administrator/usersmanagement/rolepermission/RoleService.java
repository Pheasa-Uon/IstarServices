package com.istar.reports.service.administrator.usersmanagement.rolepermission;


import com.istar.reports.entity.administrator.usersmanagement.rolepermission.Role;

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


