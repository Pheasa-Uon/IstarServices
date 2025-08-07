package com.istar.service.dto.administrator.usersmanagement.permission;

import lombok.Data;

@Data
public class FeaturePermissionDTO {

    private Long roleId;
    private Long featureId;

    private String featureCode;

    private Boolean isSearch = false;
    private Boolean isAdd = false;
    private Boolean isViewed = false;
    private Boolean isEdit = false;
    private Boolean isApprove = false;
    private Boolean isReject = false;
    private Boolean isDeleted = false;
    private Boolean isSave = false;
    private Boolean isClear = false;
    private Boolean isCancel = false;
    private Boolean isProcess = false;
    private Boolean isImport = false;
    private Boolean isExport = false;
    private Boolean bStatus;

    public FeaturePermissionDTO() {
    }

    public FeaturePermissionDTO(String code, Boolean isSearch, Boolean isAdd,
                                Boolean isViewed, Boolean isEdit, Boolean isDeleted) {
        this.featureCode = code;
        this.isSearch = isSearch;
        this.isAdd = isAdd;
        this.isViewed = isViewed;
        this.isEdit = isEdit;
        this.isDeleted = isDeleted;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public Boolean getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(Boolean isSearch) {
        this.isSearch = isSearch;
    }

    public Boolean getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Boolean isAdd) {
        this.isAdd = isAdd;
    }

    public Boolean getIsViewed() {
        return isViewed;
    }

    public void setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
    }

    public Boolean getIsReject() {
        return isReject;
    }

    public void setIsReject(Boolean isReject) {
        this.isReject = isReject;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsSave() {
        return isSave;
    }

    public void setIsSave(Boolean isSave) {
        this.isSave = isSave;
    }

    public Boolean getIsClear() {
        return isClear;
    }

    public void setIsClear(Boolean isClear) {
        this.isClear = isClear;
    }

    public Boolean getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Boolean isCancel) {
        this.isCancel = isCancel;
    }

    public Boolean getIsProcess() {
        return isProcess;
    }

    public void setIsProcess(Boolean isProcess) {
        this.isProcess = isProcess;
    }

    public Boolean getIsImport() {
        return isImport;
    }

    public void setIsImport(Boolean isImport) {
        this.isImport = isImport;
    }

    public Boolean getIsExport() {
        return isExport;
    }

    public void setIsExport(Boolean isExport) {
        this.isExport = isExport;
    }

    public Boolean getBStatus() {
        return bStatus;
    }

    public void setBStatus(Boolean bStatus) {
        this.bStatus = bStatus;
    }
}
