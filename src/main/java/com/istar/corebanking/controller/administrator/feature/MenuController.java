package com.istar.corebanking.controller.administrator.feature;

import com.istar.corebanking.dto.administrator.feature.MenuTreeDTO;
import com.istar.corebanking.entity.administrator.feature.Menu;
import com.istar.corebanking.service.administrator.feature.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/coregateways/mainmenu")
public class MenuController {

    @Autowired
    private MenuService mainMenuService;

    @GetMapping("/treetable")
    public ResponseEntity<List<MenuTreeDTO>> getAllMainMenuTree() {
        return ResponseEntity.ok(mainMenuService.getAllMainMenuTree());
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMainMenu(){ return ResponseEntity.ok(mainMenuService.getAllMainMenu());}

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMainMenuById(@PathVariable Long id) {
        return mainMenuService.getMainMenuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Menu createdMainMenu(@RequestBody Menu mainMenu){
        mainMenu.setCreatedAt(LocalDateTime.now());
        mainMenu.setUpdatedAt(LocalDateTime.now());
        mainMenu.setBStatus(true);

        return mainMenuService.createdMainMenu(mainMenu);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Menu> updatedMainMenu(@PathVariable Long id, @RequestBody Menu mainMenu){
        return ResponseEntity.ok(mainMenuService.updatedMainMenu(id, mainMenu));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMainMenu(@PathVariable Long id) {
        mainMenuService.deletedMainMenu(id);
        return ResponseEntity.ok().build();
    }
}
