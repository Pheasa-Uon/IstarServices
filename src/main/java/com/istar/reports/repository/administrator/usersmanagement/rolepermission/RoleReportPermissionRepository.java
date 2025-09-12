package com.istar.reports.repository.administrator.usersmanagement.rolepermission;

import com.istar.reports.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.reports.entity.administrator.usersmanagement.rolepermission.RoleReportPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleReportPermissionRepository extends JpaRepository<RoleReportPermission, Long> {

    List<RoleReportPermission> findByRoleId(Long roleId);
    Optional<RoleReportPermission> findByRoleIdAndReportId(Long roleId, Long reportId);
    List<RoleReportPermission> findByRole (Role role);

    @Query("SELECT rrp FROM RoleReportPermission rrp WHERE rrp.role.id IN :roleIds AND rrp.bStatus = true")
    List<RoleReportPermission> findByRoleIdInAndBStatusIsTrue(@Param("roleIds") List<Long> roleIds);

    @Query("SELECT rrp FROM RoleReportPermission rrp WHERE rrp.role.id IN :roleIds AND rrp.bStatus = true")
    List<RoleReportPermission> findByRoles(@Param("roleIds") Collection<Role> role);

}
