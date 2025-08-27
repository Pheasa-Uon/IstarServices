package com.istar.service.repository.administrator.usersmanagement.permission;

import com.istar.service.entity.administrator.usersmanagement.permission.Role;
import com.istar.service.entity.administrator.usersmanagement.permission.RoleMainMenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleMainMenuPermissionRepository extends JpaRepository<RoleMainMenuPermission, Long> {

    List<RoleMainMenuPermission> findByRoleId(Long roleId);
    Optional<RoleMainMenuPermission> findByRoleIdAndMainMenuId(Long roleId, Long mainMenuId);
    List<RoleMainMenuPermission> findByRole (Role role);
    List<RoleMainMenuPermission> findByRoleIdIn(List<Long> roleIds);

    @Query("SELECT rm FROM RoleMainMenuPermission rm WHERE rm.role.id IN :roleIds AND rm.bStatus = true")
    List<RoleMainMenuPermission> findByRoleIdInAndBStatusIsTrue(List<Long> roleIds);
    @Query("SELECT rm FROM RoleMainMenuPermission rm WHERE rm.role.id IN :roleIds AND rm.bStatus = true")
    List<RoleMainMenuPermission> findByRoles(List<Role> role);
}
