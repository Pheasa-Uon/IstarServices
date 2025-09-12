package com.istar.reports.controller.administrator.usersmanagement.rolepermission;

import com.istar.reports.dto.administrator.usersmanagement.rolepermission.PermissionBulkDTO;
import com.istar.reports.dto.administrator.usersmanagement.rolepermission.RolePermissionsDTO;
import com.istar.reports.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;
import com.istar.reports.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.reports.repository.administrator.usersmanagement.rolepermission.RoleRepository;
import com.istar.reports.service.administrator.usersmanagement.rolepermission.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coregateways/rolepermissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService permissionService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<RoleFeaturePermission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

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

    @PostMapping("/bulk")
    public ResponseEntity<?> savePermissionsBulk(@RequestBody PermissionBulkDTO bulkDTO) {
        permissionService.savePermissionsBulk(
                bulkDTO.getFeaturePermissions(),
                bulkDTO.getMainMenuPermissions(),
                bulkDTO.getReportPermissions()
        );
        return ResponseEntity.ok().build();
    }

}