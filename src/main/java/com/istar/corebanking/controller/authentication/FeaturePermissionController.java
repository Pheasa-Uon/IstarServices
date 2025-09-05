package com.istar.corebanking.controller.authentication;

import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.corebanking.service.authentication.FeaturePermissionFlags;
import com.istar.corebanking.service.authentication.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/me/check")
    public boolean checkFeaturePermissions(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam String featureCode,
                                           @RequestParam String permissionType){
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return permissionService.hasFeaturePermission(user.getRoles(), featureCode, permissionType);
    }
}
