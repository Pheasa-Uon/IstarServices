package com.istar.corebanking.dto.administrator.usersmanagement.rolepermission;

import java.util.List;
public class PermissionBulkDTO {

    private List<RoleFeaturePermissionDTO> featurePermissions;
    private List<RoleMenuPermissionDTO> mainMenuPermissions;
    private List<RoleReportPermissionDTO> reportPermissions; // FIXED (plural)

    // getters & setters
    public List<RoleFeaturePermissionDTO> getFeaturePermissions() { return featurePermissions; }
    public void setFeaturePermissions(List<RoleFeaturePermissionDTO> featurePermissions) { this.featurePermissions = featurePermissions; }

    public List<RoleMenuPermissionDTO> getMainMenuPermissions() { return mainMenuPermissions; }
    public void setMainMenuPermissions(List<RoleMenuPermissionDTO> mainMenuPermissions) { this.mainMenuPermissions = mainMenuPermissions; }

    public List<RoleReportPermissionDTO> getReportPermissions() { return reportPermissions; }
    public void setReportPermissions(List<RoleReportPermissionDTO> reportPermissions) { this.reportPermissions = reportPermissions; }
}
