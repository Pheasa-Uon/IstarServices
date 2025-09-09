package com.istar.corebanking.service.administrator.feature;

import com.istar.corebanking.dto.administrator.feature.MenuTreeDTO;
import com.istar.corebanking.entity.administrator.feature.Menu;

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
