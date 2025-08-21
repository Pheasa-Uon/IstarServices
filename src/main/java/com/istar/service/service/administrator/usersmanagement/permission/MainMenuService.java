package com.istar.service.service.administrator.usersmanagement.permission;

import com.istar.service.dto.administrator.usersmanagement.permission.MainMenuTreeDTO;
import com.istar.service.entity.administrator.usersmanagement.permission.Feature;
import com.istar.service.entity.administrator.usersmanagement.permission.MainMenu;

import java.util.List;

public interface MainMenuService {

    List<MainMenuTreeDTO> getAllMainMenus();
}
