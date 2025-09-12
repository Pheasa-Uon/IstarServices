package com.istar.reports.service.administrator.feature;

import com.istar.reports.dto.administrator.feature.MenuTreeDTO;
import com.istar.reports.entity.administrator.feature.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    List<MenuTreeDTO> getAllMainMenuTree();
    List<Menu> getAllMainMenu();
    Optional<Menu> getMainMenuById(Long id);
    Menu createdMainMenu(Menu mainMenu);
    Menu updatedMainMenu(Long id, Menu updated);
    void deletedMainMenu(Long id);


}
