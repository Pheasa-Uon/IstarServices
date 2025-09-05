package com.istar.corebanking.service.authentication;

public record ReportPermissionFlags(Boolean canView, Boolean canExport) {
    public ReportPermissionFlags or(ReportPermissionFlags other) {
        return new ReportPermissionFlags(
                this.canView || other.canView,
                this.canExport || other.canExport
        );
    }
}
