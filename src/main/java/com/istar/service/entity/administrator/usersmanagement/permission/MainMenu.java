package com.istar.service.entity.administrator.usersmanagement.permission;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Entity
@Table(name = "sys_main_menu")
@Getter
@Setter
public class MainMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "main_menu_code", unique = true)
    private String code;
    @Column(name = "main_menu_name", unique = true, nullable = false, length = 250)
    private String name;
    @Column(name = "route_path", nullable = false, length = 250)
    private String routePath;
    @Column(name = "main_menu_icon", nullable = false, length = 250)
    private String icon;
    @Column(name = "orders", nullable = false)
    private Integer orders;
    @Column(name = "parent_id", nullable = false)
    private Long parentId;
    @Column(name = "bstatus", nullable = false)
    private Boolean bStatus;
    @Column(name = "created_at", updatable = false)
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column(name = "main_menu_description", nullable = false, length = 500)
    private String description;

}
