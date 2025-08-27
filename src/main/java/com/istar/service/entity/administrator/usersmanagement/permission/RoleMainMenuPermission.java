package com.istar.service.entity.administrator.usersmanagement.permission;


import com.istar.service.entity.administrator.feature.MainMenu;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_role_menu_permissions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "main_menu_id"}))
@Data
public class RoleMainMenuPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "main_menu_id", nullable = false)
    private MainMenu mainMenu;

    private Boolean isVisible;
    private Boolean bStatus;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public MainMenu getMainMenu() { return mainMenu; }
    public void setMainMenu(MainMenu mainMenu) { this.mainMenu = mainMenu; }

    public Boolean getIsVisible() { return isVisible; }
    public void setIsVisible(Boolean isVisible) { this.isVisible = isVisible; }

    public Boolean getBStatus() { return bStatus; }
    public void setBStatus(Boolean bStatus) { this.bStatus = bStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }


}
