package com.istar.corebanking.service.administrator.usersmanagement.permission;

import com.istar.corebanking.entity.administrator.feature.Reports;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleReportPermission;
import com.istar.corebanking.repository.administrator.feature.ReportRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleReportPermissionRepository;
import com.istar.corebanking.service.administrator.usersmanagement.permission.record.ReportPermissionFlags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportPermissionService {

    private final RoleReportPermissionRepository rrRepo;

    private final ReportRepository reportRepository;

    // =========== REPORT PERMISSIONS ===========

    // Return merged report permissions for a user's roles
    public Map<Long, ReportPermissionFlags> mergeReportPermissions(Set<Role> roles) {
        Map<Long, ReportPermissionFlags> map = new HashMap<>();

        // Convert role set to a list of IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleReportPermission entries
        for (RoleReportPermission rrp : rrRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            Long reportId = rrp.getReport().getId();

            ReportPermissionFlags flags = new ReportPermissionFlags(
                    rrp.getIsViewed(),
                    rrp.getIsExport()
            );
            // Merge permissions for the same report using Or Logic
            map.merge(reportId, flags, ReportPermissionFlags::or);
        }
        return map;
    }

    // Return all report permissions (full access for admin)
    public Map<Long, ReportPermissionFlags> getAllReportPermissions() {
        Map<Long, ReportPermissionFlags> all = new HashMap<>();

        // Fetch all reports from DB
        List<Reports> reports = reportRepository.findAll();

        for (Reports report : reports) {
            // Admin has full access to all reports
            all.put(report.getId(), new ReportPermissionFlags(true,true));
        }
        return all;
    }

    // Get accessible report IDs for a user (can view or export)
    public Set<Long> getAccessibleReportIds(Set<Role> roles) {
        Map<Long, ReportPermissionFlags> reportPermissions = mergeReportPermissions(roles);
        return reportPermissions.entrySet().stream()
                .filter(entry -> entry.getValue().canView() || entry.getValue().canExport())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

}
