package com.istar.corebanking.controller.administrator.usersmanagement.auth;

import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.corebanking.service.administrator.usersmanagement.rolepermission.FeaturePermissionFlags;
import com.istar.corebanking.service.administrator.usersmanagement.rolepermission.PermissionService;
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
        User user = getUserFromDetails(userDetails);
    }

}
