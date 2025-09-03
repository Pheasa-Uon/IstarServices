package com.istar.corebanking.service.administrator.usersmanagement.rolepermission;

public record ReportPermissionFlags(Boolean canView, Boolean canExport) {
    public ReportPermissionFlags or(ReportPermissionFlags other) {
        return new ReportPermissionFlags(
                this.canView || other.canView,
                this.canExport || other.canExport
        );
    }
}
