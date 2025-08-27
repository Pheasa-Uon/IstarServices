package com.istar.service.service.administrator.feature.impl;

import com.istar.service.dto.administrator.feature.MainMenuTreeDTO;
import com.istar.service.entity.administrator.feature.MainMenu;
import com.istar.service.repository.administrator.feature.MainMenuRepository;
import com.istar.service.service.administrator.feature.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainMenuServiceImpl implements MainMenuService {

    @Autowired
    private MainMenuRepository mainMenuRepository;

    public MainMenuServiceImpl() {}

    public List<MainMenuTreeDTO> getAllMainMenuTree() {
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
        dto.setActive(mainMenu.getBStatus());
        dto.setDisplayOrder(mainMenu.getOrders());

        if (mainMenu.getChildren() != null && !mainMenu.getChildren().isEmpty()) {
            List<MainMenuTreeDTO> childDTOs = mainMenu.getChildren().stream()
                    .map(this::convertToTreeDTO).collect(Collectors.toList());
            dto.setChildren(childDTOs);
        }
        return dto;
    }

    public List<MainMenu> getAllMainMenu(){return mainMenuRepository.findAll();}

    public MainMenu createdMainMenu(MainMenu mainMenu){
        mainMenu.setCreatedAt(LocalDateTime.now());
        mainMenu.setUpdatedAt(LocalDateTime.now());
        mainMenu.setBStatus(true);

        return mainMenuRepository.save(mainMenu);
    }

    public MainMenu updatedMainMenu(Long id,MainMenu updated){
        return mainMenuRepository.findById(id).map(mainMenu -> {
            mainMenu.setCode(updated.getCode());
            mainMenu.setName(updated.getName());
            mainMenu.setIcon(updated.getIcon());
            mainMenu.setRoutePath(updated.getRoutePath());
            mainMenu.setParent(updated.getParent());
            mainMenu.setUpdatedAt(LocalDateTime.now());
            return mainMenuRepository.save(mainMenu);
        }).orElseThrow(() -> new RuntimeException("Main Menu is not found with ID " + id));

    }

    public void deletedMainMenu(Long id){
        mainMenuRepository.deleteById(id);
    }

    public Optional<MainMenu> getMainMenuById(Long id) {
        return mainMenuRepository.findById(id);
    }
}
