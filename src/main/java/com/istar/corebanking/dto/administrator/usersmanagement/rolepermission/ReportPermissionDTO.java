package com.istar.corebanking.dto.administrator.usersmanagement.rolepermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPermissionDTO {
    private Long roleId;
    private Long reportId;
    private String reportCode;
    private Boolean isViewed = false;
    private Boolean isExport = false;
    private Boolean bStatus = false;
}
