package com.istar.corebanking.controller.administrator.usersmanagement.permission;


import com.istar.corebanking.dto.administrator.usersmanagement.permission.MenuPermissionDTO;
import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.repository.administrator.feature.MenuRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.corebanking.service.administrator.usersmanagement.permission.MenuPermissionService;
import com.istar.corebanking.service.administrator.usersmanagement.permission.record.MenuPermissionFlags;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/coregateways/permissions/menus")
@RequiredArgsConstructor
public class MenuPermissionController {

    private final MenuPermissionService permissionService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public Map<String, MenuPermissionDTO> getMyMenuPermissions(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");

        return isAdmin ? permissionService.getAllMenuPermissions() : permissionService.mergeByMenu(user.getRoles());
    }

}
