package com.istar.corebanking.dto.administrator.feature;

import java.util.ArrayList;
import java.util.List;

public class ReportTreeDTO {

    private Long id;
    private String code;
    private String name;
    private String routePath;
    private Boolean active; // clearer than bStatus
    private Integer displayOrder; // safer than 'order'
    private String description; // singular for consistency
    private List<MainMenuTreeDTO> children = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoutePath() { return routePath; }
    public void setRoutePath(String routePath) { this.routePath = routePath; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<MainMenuTreeDTO> getChildren() { return children; }
    public void setChildren(List<MainMenuTreeDTO> children) { this.children = children; }
}
