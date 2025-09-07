package com.istar.corebanking.service.administrator.usersmanagement.permission;

public record MenuPermissionFlags(boolean isvisible) {
    public MenuPermissionFlags or(MenuPermissionFlags other) {
        return new MenuPermissionFlags(
                this.isvisible || other.isvisible
        );
    }
}
