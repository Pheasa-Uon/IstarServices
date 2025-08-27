package com.istar.service.dto.administrator.usersmanagement.permission;

import java.util.List;

public class PermissionBulkDTO {

    private List<FeaturePermissionDTO> featurePermissions;
    private List<MainMenuPermissionDTO> mainMenuPermissions;

    // Getters & Setters
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
}
