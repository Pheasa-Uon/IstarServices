package com.istar.reports.repository.administrator.feature;

import com.istar.reports.entity.administrator.feature.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByCode(String code);
    List<Menu> findByParentIsNull();
}
