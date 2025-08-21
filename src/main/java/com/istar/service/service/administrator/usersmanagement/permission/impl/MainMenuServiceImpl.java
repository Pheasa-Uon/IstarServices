package com.istar.service.service.administrator.usersmanagement.permission.impl;

import com.istar.service.dto.administrator.usersmanagement.permission.FeatureTreeDTO;
import com.istar.service.dto.administrator.usersmanagement.permission.MainMenuTreeDTO;
import com.istar.service.entity.administrator.usersmanagement.permission.Feature;
import com.istar.service.entity.administrator.usersmanagement.permission.MainMenu;
import com.istar.service.repository.administrator.usersmanagement.permission.MainMenuRepository;
import com.istar.service.service.administrator.usersmanagement.permission.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainMenuServiceImpl implements MainMenuService {

    @Autowired
    private MainMenuRepository mainMenuRepository;

    public MainMenuServiceImpl() {}

    public List<MainMenuTreeDTO> getAllMainMenus() {
        List<MainMenu> mainMenus = mainMenuRepository.findByParentIsNull();
        return mainMenus.stream().map(this::convertToTreeDTO).collect(Collectors.toList());
    }

    private MainMenuTreeDTO convertToTreeDTO(MainMenu mainMenu) {
        MainMenuTreeDTO dto = new MainMenuTreeDTO();
        dto.setId(mainMenu.getId());
        dto.setName(mainMenu.getName());
        dto.setCode(mainMenu.getCode());
        dto.setRoutePath(mainMenu.getRoutePath());
        dto.setIcon(mainMenu.getIcon());
        dto.setBStatus(mainMenu.getBStatus());
        dto.setOrder(mainMenu.getOrders());

        if (mainMenu.getChildren() != null && !mainMenu.getChildren().isEmpty()) {
            List<MainMenuTreeDTO> childDTOs = mainMenu.getChildren().stream()
                    .map(this::convertToTreeDTO).collect(Collectors.toList());
            dto.setChildren(childDTOs);
        }
        return dto;
    }
}
