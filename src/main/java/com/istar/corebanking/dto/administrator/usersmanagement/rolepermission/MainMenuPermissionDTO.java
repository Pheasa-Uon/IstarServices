package com.istar.corebanking.dto.administrator.usersmanagement.rolepermission;

public class MainMenuPermissionDTO {
    private Long roleId;
    private Long mainMenuId;
    private Boolean isVisible;

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public Long getMainMenuId() { return mainMenuId; }
    public void setMainMenuId(Long mainMenuId) { this.mainMenuId = mainMenuId; }

    public Boolean getIsVisible() { return isVisible; }
    public void setIsVisible(Boolean isVisible) { this.isVisible = isVisible; }
}
