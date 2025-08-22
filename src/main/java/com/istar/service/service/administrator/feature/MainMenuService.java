package com.istar.service.service.administrator.feature;

import com.istar.service.dto.administrator.feature.MainMenuTreeDTO;
import com.istar.service.entity.administrator.usersmanagement.permission.MainMenu;

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
