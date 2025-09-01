package com.istar.corebanking.controller.administrator.usersmanagement.user;


import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.user.UserRole;
import com.istar.corebanking.service.administrator.usersmanagement.user.UserRoleService;
import com.istar.corebanking.dto.administrator.usersmanagement.user.RoleAssignmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coregateways/userroles")
//@PreAuthorize("hasRole('ADMIN')")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/assign")
    public ResponseEntity<UserRole> assignRole(@RequestBody RoleAssignmentRequest request) {
        UserRole userRole = userRoleService.assignRoleToUser(request.userId, request.roleId);
        System.out.println("Assigned role: " + userRole.getRole().getName() + " to user: " + userRole.getUser().getUsername());
        return ResponseEntity.ok(userRole);
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeRole(@RequestBody RoleAssignmentRequest request) {
        userRoleService.removeRoleFromUser(request.userId, request.roleId);
        System.out.println("Removed role: " + request.roleId + " from user: " + request.userId);
        return ResponseEntity.ok("Role removed from user");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable Long userId) {
        List<Role> roles = userRoleService.getUserRoles(userId);
        return ResponseEntity.ok(roles);
    }
}
