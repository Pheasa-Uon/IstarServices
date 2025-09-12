package com.istar.reports.service.administrator.usersmanagement.permission.record;

public record MenuPermissionFlags(boolean isvisible) {
    public MenuPermissionFlags or(MenuPermissionFlags other) {
        return new MenuPermissionFlags(
                this.isvisible || other.isvisible
        );
    }
}
