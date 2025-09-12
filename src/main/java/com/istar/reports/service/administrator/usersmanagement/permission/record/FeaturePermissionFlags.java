package com.istar.reports.service.administrator.usersmanagement.permission.record;

public record FeaturePermissionFlags(boolean search, boolean add, boolean view, boolean edit, boolean approve, boolean reject, boolean deleted, boolean save, boolean clear, boolean cancel, boolean process, boolean imported, boolean exported) {
    public FeaturePermissionFlags or(FeaturePermissionFlags other) {
        return new FeaturePermissionFlags(
                this.search || other.search,
                this.add || other.add,
                this.view || other.view,
                this.edit || other.edit,
                this.approve || other.approve,
                this.reject || other.reject,
                this.deleted || other.deleted,
                this.save || other.save,
                this.clear || other.clear,
                this.cancel || other.cancel,
                this.process || other.process,
                this.imported || other.imported,
                this.exported || other.exported
        );
    }
}
