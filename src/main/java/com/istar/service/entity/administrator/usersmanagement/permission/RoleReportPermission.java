package com.istar.service.entity.administrator.usersmanagement.permission;

import com.istar.service.entity.administrator.feature.Feature;
import com.istar.service.entity.administrator.feature.Reports;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_role_report_permissions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "report_id"}))
@Data
public class RoleReportPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "report_id", nullable = false)
    private Reports report;

    private Boolean isSearch = false;
    private Boolean isViewed = false;
    private Boolean isExport = false;

    private Boolean bStatus;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

}
