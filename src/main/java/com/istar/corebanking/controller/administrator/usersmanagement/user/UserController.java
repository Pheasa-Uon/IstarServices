package com.istar.corebanking.controller.administrator.usersmanagement.user;

import com.istar.corebanking.entity.administrator.usersmanagement.user.User;
import com.istar.corebanking.service.administrator.usersmanagement.user.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "${app.cors.allowed-origins}")
@RestController
@RequestMapping("/api/coregateways/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    @PostMapping
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setBStatus(true);

        //System.out.println("registerUser: " + user);
        return userService.saveUser(user);
    }

    // Get all users
    @GetMapping
    public List<User> getUsers() {
        System.out.println("Getting all users...");
        return userService.getAllUsers();
    }

    // Get status labels
    @GetMapping("/status")
    public Map<String, String> getStatusLabels() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("A", "Active");
        statusMap.put("B", "Blocked");
        statusMap.put("C", "Closed");
        return statusMap;
    }

    // Update user by ID
    //@PreAuthorize("hasAuthority('USR_EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        System.out.println(updated);
        return ResponseEntity.ok(updated);
    }

    // ✅ Fixed: Reset user password (now path is correct)
    @PutMapping("/{id}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable("id") Long id) {
        userService.resetPassword(id);
        return ResponseEntity.ok("Password has been reset.");
    }

    // Soft delete a user
    //@PreAuthorize("hasAuthority('USR_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("User has been soft-deleted.");
    }

    // Search users by keyword
    //@PreAuthorize("hasAuthority('USR_SEARCH')")
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String keyword) {
        System.out.println(keyword);
        return userService.searchUsers(keyword);
    }
}
