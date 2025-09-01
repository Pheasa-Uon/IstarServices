package com.istar.corebanking.service.administrator.feature;

import com.istar.corebanking.dto.administrator.feature.MainMenuTreeDTO;
import com.istar.corebanking.entity.administrator.feature.MainMenu;

import java.util.List;
import java.util.Optional;

public interface MainMenuService {

    List<MainMenuTreeDTO> getAllMainMenuTree();
    List<MainMenu> getAllMainMenu();
    Optional<MainMenu> getMainMenuById(Long id);
    MainMenu createdMainMenu(MainMenu mainMenu);
    MainMenu updatedMainMenu(Long id, MainMenu updated);
    void deletedMainMenu(Long id);


}
