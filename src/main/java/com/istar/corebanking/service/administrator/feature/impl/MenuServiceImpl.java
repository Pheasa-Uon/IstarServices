package com.istar.corebanking.service.administrator.feature.impl;

import com.istar.corebanking.dto.administrator.feature.MenuTreeDTO;
import com.istar.corebanking.entity.administrator.feature.Menu;
import com.istar.corebanking.repository.administrator.feature.MenuRepository;
import com.istar.corebanking.service.administrator.feature.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository mainMenuRepository;

    public MenuServiceImpl() {}

    public List<MenuTreeDTO> getAllMainMenuTree() {
        List<Menu> mainMenus = mainMenuRepository.findByParentIsNull();
        return mainMenus.stream().map(this::convertToTreeDTO).collect(Collectors.toList());
    }

    private MenuTreeDTO convertToTreeDTO(Menu mainMenu) {
        MenuTreeDTO dto = new MenuTreeDTO();
        dto.setId(mainMenu.getId());
        dto.setName(mainMenu.getName());
        dto.setCode(mainMenu.getCode());
        dto.setRoutePath(mainMenu.getRoutePath());
        dto.setIcon(mainMenu.getIcon());
        dto.setActive(mainMenu.getBStatus());
        dto.setDisplayOrder(mainMenu.getOrders());
        dto.setDescription(mainMenu.getDescription());

        if (mainMenu.getChildren() != null && !mainMenu.getChildren().isEmpty()) {
            List<MenuTreeDTO> childDTOs = mainMenu.getChildren().stream()
                    .map(this::convertToTreeDTO).collect(Collectors.toList());
            dto.setChildren(childDTOs);
        }
        return dto;
    }

    public List<Menu> getAllMainMenu(){return mainMenuRepository.findAll();}

    public Menu createdMainMenu(Menu mainMenu){
        mainMenu.setCreatedAt(LocalDateTime.now());
        mainMenu.setUpdatedAt(LocalDateTime.now());
        mainMenu.setBStatus(true);

        return mainMenuRepository.save(mainMenu);
    }

    public Menu updatedMainMenu(Long id, Menu updated){
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

    public Optional<Menu> getMainMenuById(Long id) {
        return mainMenuRepository.findById(id);
    }
}
