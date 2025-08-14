package com.istar.service.service.administrator.usersmanagement.permission;


public record PermissionFlags(boolean search, boolean view, boolean add, boolean edit, boolean delete) {
    public PermissionFlags or(PermissionFlags other) {
        return new PermissionFlags(
                this.search || other.search,
                this.view || other.view,
                this.add || other.add,
                this.edit || other.edit,
                this.delete || other.delete
        );
    }
}
