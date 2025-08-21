package com.istar.service.dto.administrator.usersmanagement.permission;

import java.util.ArrayList;
import java.util.List;

public class MainMenuTreeDTO {
    private Long id;
    private String code;
    private String name;
    private String routePath;
    private String icon;
    private Boolean bStatus;
    private Integer order;
    private String descriptions;
    private List<MainMenuTreeDTO> children = new ArrayList<>();

    // Getter and Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRoutePath() { return routePath; }
    public void setRoutePath(String routePath) { this.routePath = routePath; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public Boolean getBStatus() { return bStatus; }
    public void setBStatus(Boolean bStatus) { this.bStatus = bStatus; }
    public Integer getOrder() { return order; }
    public void setOrder(Integer order) { this.order = order; }
    public String getDescriptions() { return descriptions; }
    public void setDescriptions(String descriptions) { this.descriptions = descriptions; }
    public List<MainMenuTreeDTO> getChildren() { return children; }
    public void setChildren(List<MainMenuTreeDTO> children) { this.children = children; }
}
