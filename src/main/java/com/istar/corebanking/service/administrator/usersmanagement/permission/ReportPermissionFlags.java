package com.istar.corebanking.service.administrator.usersmanagement.permission;

public record ReportPermissionFlags(Boolean canView, Boolean canExport) {
    public ReportPermissionFlags or(ReportPermissionFlags other) {
        return new ReportPermissionFlags(
                this.canView || other.canView,
                this.canExport || other.canExport
        );
    }
}
