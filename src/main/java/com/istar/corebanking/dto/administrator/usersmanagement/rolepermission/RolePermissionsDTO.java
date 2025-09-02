package com.istar.corebanking.dto.administrator.usersmanagement.rolepermission;

import java.util.List;

public class RolePermissionsDTO {

    private List<FeaturePermissionDTO> featurePermissions;
    private List<MainMenuPermissionDTO> mainMenuPermissions;
    private List<ReportPermissionDTO> reportPermissions;

    public List<FeaturePermissionDTO> getFeaturePermissions() {
        return featurePermissions;
    }
    public void setFeaturePermissions(List<FeaturePermissionDTO> featurePermissions) {
        this.featurePermissions = featurePermissions;
    }

    public List<MainMenuPermissionDTO> getMainMenuPermissions() {
        return mainMenuPermissions;
    }
    public void setMainMenuPermissions(List<MainMenuPermissionDTO> mainMenuPermissions) {
        this.mainMenuPermissions = mainMenuPermissions;
    }

    public List<ReportPermissionDTO> getReportPermissions() {
        return reportPermissions;
    }
    public void setReportPermissions(List<ReportPermissionDTO> reportPermissions) {
        this.reportPermissions = reportPermissions;
    }
}
