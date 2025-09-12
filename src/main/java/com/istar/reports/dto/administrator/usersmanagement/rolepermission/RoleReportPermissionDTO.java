package com.istar.reports.dto.administrator.usersmanagement.rolepermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleReportPermissionDTO {
    private Long roleId;
    private Long reportId;
    private String reportCode;
    private Boolean isViewed = false;
    private Boolean isExport = false;
    private Boolean bStatus = false;

    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getReportId() {
        return reportId;
    }
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public Boolean getIsViewed() {
        return isViewed;
    }

    public void setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
    }
    public Boolean getIsExport() {
        return isExport;
    }
    public void setIsExport(Boolean isExport) {
        this.isExport = isExport;
    }

    public Boolean getbStatus() {
        return bStatus;
    }
    public void setbStatus(Boolean bStatus) {
        this.bStatus = bStatus;
    }
}
