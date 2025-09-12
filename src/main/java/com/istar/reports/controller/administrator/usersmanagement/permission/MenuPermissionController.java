package com.istar.reports.controller.administrator.usersmanagement.permission;

import com.istar.reports.dto.administrator.usersmanagement.permission.MenuPermissionDTO;
import com.istar.reports.entity.administrator.usersmanagement.user.User;
import com.istar.reports.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.reports.service.administrator.usersmanagement.permission.MenuPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coregateways/permissions/menus")
@RequiredArgsConstructor
public class MenuPermissionController {

    private final MenuPermissionService permissionService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public List<MenuPermissionDTO> getMyMenuPermissions(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equalsIgnoreCase("admin");

        return isAdmin
                ? permissionService.getAllMenuPermissions()
                : permissionService.mergeByMenu(user.getRoles());
    }
}
