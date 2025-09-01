package com.istar.corebanking.controller.administrator.usersmanagement.rolepermission;

import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.PermissionBulkDTO;
import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.RolePermissionsDTO;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleRepository;
import com.istar.corebanking.service.administrator.usersmanagement.rolepermission.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coregateways/permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService permissionService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<RoleFeaturePermission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

//    @GetMapping("/role/{roleId}")
//    public ResponseEntity<List<FeaturePermissionDTO>> getPermissionsByRole(@PathVariable Long roleId) {
//        Role role = roleRepository.findById(roleId).orElse(null);
//        if (role == null) {
//            return ResponseEntity.notFound().build();
//        }
//        System.out.println("Role found: " + role.getName());
//        List<FeaturePermissionDTO> permissions = permissionService.getPermissionsByRole(role);
//        // Add null check and filtering
//        if (permissions != null) {
//            permissions = permissions.stream()
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//        }
//        System.out.println("Permissions found: " + permissions);
//        return ResponseEntity.ok(permissions);
//    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<RolePermissionsDTO> getPermissionsByRole(@PathVariable Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        RolePermissionsDTO permissions = permissionService.getPermissionsByRole(role);
        return ResponseEntity.ok(permissions);
    }


    @PostMapping
    public ResponseEntity<RoleFeaturePermission> createPermission(@RequestBody RoleFeaturePermission permission) {
        return ResponseEntity.ok(permissionService.createPermission(permission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleFeaturePermission> updatePermission(
            @PathVariable Long id,
            @RequestBody RoleFeaturePermission permission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, permission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/bulk")
//    public ResponseEntity<?> savePermissionsBulk(@RequestBody List<FeaturePermissionDTO> dtos, List<MainMenuPermissionDTO> dtom) {
//        permissionService.savePermissionsBulk(dtos, dtom);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/bulk")
    public ResponseEntity<?> savePermissionsBulk(@RequestBody PermissionBulkDTO bulkDTO) {
        permissionService.savePermissionsBulk(
                bulkDTO.getFeaturePermissions(),
                bulkDTO.getMainMenuPermissions()
        );
        return ResponseEntity.ok().build();
    }

}