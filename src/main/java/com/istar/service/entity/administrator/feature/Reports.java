package com.istar.service.entity.administrator.feature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_reports")
@Getter
@Setter
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_code", unique = true)
    private String code;
    @Column(name = "report_name", unique = true, nullable = false, length = 250)
    private String name;
    @Column(name = "route_path", length = 250)
    private String routePath;
    @Column(name = "report_icon", nullable = false, length = 250)
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
    private Reports parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Reports> children = new ArrayList<>();
}
