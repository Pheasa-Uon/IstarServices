package com.istar.reports.controller.administrator.usersmanagement.permission;


import com.istar.reports.entity.administrator.usersmanagement.user.User;
import com.istar.reports.repository.administrator.usersmanagement.user.UserRepository;
import com.istar.reports.service.administrator.usersmanagement.permission.ReportPermissionService;
import com.istar.reports.service.administrator.usersmanagement.permission.record.ReportPermissionFlags;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/coregateways/permissions/reports")
@RequiredArgsConstructor
public class ReportPermissionController {

    private final ReportPermissionService permissionService;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public Map<Long, ReportPermissionFlags> getMyReportPermissions(@AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");

        return isAdmin ? permissionService.getAllReportPermissions() :
                        permissionService.mergeReportPermissions(user.getRoles());

    }

//    @GetMapping("/me/accessible")
//    public Set<Long> getAccessibleReportPermissions(@AuthenticationPrincipal UserDetails userDetails) {
//
//        User user =  userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        boolean isAdmin = user.isAdmin() || user.getUsername().equals("admin");
//
//        return isAdmin ? permissionService.getAllReportPermissions().keySet() :
//                        permissionService.getAccessibleReportIds(user.getRoles());
//
//    }

//    @GetMapping("/me/check")
//    public boolean checkReportPermission(@AuthenticationPrincipal UserDetails userDetails,
//                                         @RequestParam Long reportId,
//                                         @RequestParam String permissionType) {
//
//        User user  = userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return permissionService.hasReportPermission(user.getRoles(), reportId, permissionType);
//
//    }
}
