package com.istar.service.controller.administrator.feature;

import com.istar.service.dto.administrator.usersmanagement.permission.MainMenuTreeDTO;
import com.istar.service.entity.administrator.usersmanagement.permission.Feature;
import com.istar.service.entity.administrator.usersmanagement.permission.MainMenu;
import com.istar.service.service.administrator.feature.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mainmenu")
public class MainMenuController {

    @Autowired
    private MainMenuService mainMenuService;

    @GetMapping("/treetable")
    public ResponseEntity<List<MainMenuTreeDTO>> getAllMainMenuTree() {
        return ResponseEntity.ok(mainMenuService.getAllMainMenuTree());
    }

    @GetMapping
    public ResponseEntity<List<MainMenu>> getAllMainMenu(){ return ResponseEntity.ok(mainMenuService.getAllMainMenu());}

    @GetMapping("/{id}")
    public ResponseEntity<MainMenu> getMainMenuById(@PathVariable Long id) {
        return mainMenuService.getMainMenuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MainMenu createdMainMenu(@RequestBody MainMenu mainMenu){
        mainMenu.setCreatedAt(LocalDateTime.now());
        mainMenu.setUpdatedAt(LocalDateTime.now());
        mainMenu.setBStatus(true);

        return mainMenuService.createdMainMenu(mainMenu);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MainMenu> updatedMainMenu(@PathVariable Long id, @RequestBody MainMenu mainMenu){
        return ResponseEntity.ok(mainMenuService.updatedMainMenu(id, mainMenu));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMainMenu(@PathVariable Long id) {
        mainMenuService.deletedMainMenu(id);
        return ResponseEntity.ok().build();
    }
}
