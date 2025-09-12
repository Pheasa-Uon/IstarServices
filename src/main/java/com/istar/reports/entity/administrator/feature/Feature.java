package com.istar.reports.entity.administrator.feature;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String type;

    @Column(name = "display_order") // change here
    private Integer displayOrder;   // change here

    private String routePath;
    private String icon;
    private Boolean bStatus;

    @Column(length = 500)
    private String description;

    private boolean isSearch = true;
    private boolean isAdd = true;
    private boolean isViewed = true;
    private boolean isEdit = true;
    private boolean isApprove = true;
    private boolean isReject = true;
    private boolean isDeleted = true;
    private boolean isSave = true;
    private boolean isClear = true;
    private boolean isCancel = true;
    private boolean isProcess = true;
    private boolean isImport = true;
    private boolean isExport = true;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Feature parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Feature> children = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getdisplayOrder() {
        return displayOrder;
    }

    public void setdisplayOrder(Integer order) {
        this.displayOrder = order;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getBStatus() {
        return bStatus;
    }

    public void setBStatus(Boolean bStatus) {
        this.bStatus = bStatus;
    }

    public Feature getParent() {
        return parent;
    }

    public void setParent(Feature parent) {
        this.parent = parent;
    }

    public List<Feature> getChildren() {
        return children;
    }

    public void setChildren(List<Feature> children) {
        this.children = children;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isSearch() {
        return isSearch;
    }
    public void setSearch(boolean search) {
        isSearch = search;
    }
    public boolean isAdd() {
        return isAdd;
    }
    public void setAdd(boolean add) {
        isAdd = add;
    }
    public boolean isViewed() { return isViewed; }
    public void setViewed(boolean viewed) { isViewed = viewed; }
    public boolean isEdit() { return isEdit; }
    public void setEdit(boolean edit) { isEdit = edit; }
    public boolean isApprove() { return isApprove; }
    public void setApprove(boolean approve) { isApprove = approve; }
    public boolean isReject() { return isReject; }
    public void setReject(boolean reject) { isReject = reject; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
    public boolean isSave() { return isSave; }
    public void setSave(boolean save) { isSave = save; }
    public boolean isClear() { return isClear; }
    public void setClear(boolean clear) { isClear = clear; }
    public boolean isCancel() { return isCancel; }
    public void setCancel(boolean cancel) { isCancel = cancel; }
    public boolean isProcess() { return isProcess; }
    public void setProcess(boolean process) { isProcess = process; }
    public boolean isImport() { return isImport; }
    public void setImport(boolean _import) { isImport = _import; }
    public boolean isExport() { return isExport; }
}
