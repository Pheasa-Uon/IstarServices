package com.istar.service.controller.administrator.usersmanagement.permission;

import com.istar.service.dto.administrator.usersmanagement.permission.MainMenuTreeDTO;
import com.istar.service.service.administrator.usersmanagement.permission.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mainmenus")
public class MainMenuController {

    @Autowired
    private MainMenuService mainMenuService;

    @GetMapping
    public ResponseEntity<List<MainMenuTreeDTO>> getAllMainMenus() {
        return ResponseEntity.ok(mainMenuService.getAllMainMenus());
    }
}
