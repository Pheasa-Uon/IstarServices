package com.istar.service.entity.administrator.usersmanagement.permission;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "route_path", length = 250)
    private String routePath;
    @Column(name = "main_menu_icon", nullable = false, length = 250)
    private String icon;
    @Column(name = "orders", nullable = false)
    private Integer orders;
    @Column(name = "b_status", nullable = false)
    private Boolean bStatus;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    @Column(name = "descriptions", length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private MainMenu parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MainMenu> children = new ArrayList<>();


}
