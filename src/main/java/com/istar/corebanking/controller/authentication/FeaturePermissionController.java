package com.istar.corebanking.controller.authentication;

import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.corebanking.service.administrator.usersmanagement.permission.record.FeaturePermissionFlags;
import com.istar.corebanking.service.administrator.usersmanagement.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/coregateways/permissions/feature")
@RequiredArgsConstructor
public class FeaturePermissionController {

    private final PermissionService permissionService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public Map<String, FeaturePermissionFlags> getFeaturePermissions(@AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");

        return isAdmin ? permissionService.getAllFeaturePermissions() :
                        permissionService.mergeByFeature(user.getRoles());
    }
}
