package com.istar.corebanking.repository.administrator.usersmanagement.rolepermission;

import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleMenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleMenuPermissionRepository extends JpaRepository<RoleMenuPermission, Long> {

    List<RoleMenuPermission> findByRoleId(Long roleId);
    Optional<RoleMenuPermission> findByRoleIdAndMainMenuId(Long roleId, Long mainMenuId);
    List<RoleMenuPermission> findByRole (Role role);
    List<RoleMenuPermission> findByRoleIdIn(List<Long> roleIds);

    @Query("SELECT rm FROM RoleMenuPermission rm WHERE rm.role.id IN :roleIds AND rm.bStatus = true")
    List<RoleMenuPermission> findByRoleIdInAndBStatusIsTrue(List<Long> roleIds);
    @Query("SELECT rm FROM RoleMenuPermission rm WHERE rm.role.id IN :roleIds AND rm.bStatus = true")
    List<RoleMenuPermission> findByRoles(List<Role> role);
}
