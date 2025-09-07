package com.istar.corebanking.repository.administrator.feature;

import com.istar.corebanking.entity.administrator.feature.MainMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MainMenuRepository extends JpaRepository<MainMenu, Long> {
    Optional<MainMenu> findByCode(String code);
    List<MainMenu> findByParentIsNull();
}
