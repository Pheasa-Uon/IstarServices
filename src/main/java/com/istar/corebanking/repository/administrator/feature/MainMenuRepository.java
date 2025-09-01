package com.istar.corebanking.repository.administrator.feature;

import com.istar.corebanking.entity.administrator.feature.MainMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainMenuRepository extends JpaRepository<MainMenu, Long> {
    MainMenu findByCode(String code);
    List<MainMenu> findByParentIsNull();
}
