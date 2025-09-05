package com.istar.corebanking.controller.authentication;


import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.corebanking.service.authentication.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/coregateways/permissions/menus")
@RequiredArgsConstructor
public class MenuPermissionController {

    private final PermissionService permissionService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public Map<Long, Boolean> getMyMenuPermissions(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");

        return isAdmin ? permissionService.getAllMenuPermissions() :
                        permissionService.mergeMenuPermissions(user.getRoles());
    }

    @GetMapping("/me/visible")
    public Set<Long> getVisibleMenuPermissions(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");

        return isAdmin ? permissionService.getAllMenuPermissions().keySet() :
                        permissionService.getVisibleMenuIds(user.getRoles());
    }

    @GetMapping("/me/check")
    public boolean checkMyMenuPermissions(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestParam Long menuId) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return permissionService.hasMenuAccess(user.getRoles(), menuId);
    }
}
