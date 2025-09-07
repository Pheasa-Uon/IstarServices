package com.istar.corebanking.controller.authentication;


import com.istar.corebanking.entity.administrator.feature.MainMenu;
import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.repository.administrator.feature.MainMenuRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.corebanking.service.administrator.usersmanagement.permission.MenuPermissionFlags;
import com.istar.corebanking.service.administrator.usersmanagement.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coregateways/permissions/menus")
@RequiredArgsConstructor
public class MenuPermissionController {

    private final PermissionService permissionService;
    private final UserRepository userRepository;
    private final MainMenuRepository mainMenuRepository;

    @GetMapping("/me")
    public Map<String, MenuPermissionFlags> getMyMenuPermissions(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");

        return isAdmin ? permissionService.getAllMenuPermissions() :
                        permissionService.mergeByMenu(user.getRoles());
    }
}
