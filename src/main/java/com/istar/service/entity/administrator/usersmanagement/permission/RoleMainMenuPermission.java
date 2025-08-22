package com.istar.service.entity.administrator.usersmanagement.permission;

import com.istar.service.entity.administrator.feature.Feature;
import com.istar.service.entity.administrator.feature.MainMenu;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_role_menu_permissions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "menu_id"}))
@Data
public class RoleMainMenuPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(optional = false)
    //@JoinColumn(name = "feature_id", nullable = false)
    private MainMenu mainmenu;

    private Boolean bStatus;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

}
