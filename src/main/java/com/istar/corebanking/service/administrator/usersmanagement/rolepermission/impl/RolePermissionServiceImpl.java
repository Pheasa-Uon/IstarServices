package com.istar.corebanking.service.administrator.usersmanagement.rolepermission.impl;

import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.FeaturePermissionDTO;
import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.MainMenuPermissionDTO;
import com.istar.corebanking.dto.administrator.usersmanagement.rolepermission.RolePermissionsDTO;
import com.istar.corebanking.entity.administrator.feature.Feature;
import com.istar.corebanking.entity.administrator.feature.MainMenu;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleMainMenuPermission;
import com.istar.corebanking.repository.administrator.feature.FeatureRepository;
import com.istar.corebanking.repository.administrator.feature.MainMenuRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleFeaturePermissionRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleMainMenuPermissionRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleRepository;
import com.istar.corebanking.service.administrator.usersmanagement.rolepermission.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {


    @Autowired
    private RoleFeaturePermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private RoleFeaturePermissionRepository roleFeaturePermissionRepository;

    @Autowired
    private MainMenuRepository mainMenuRepository;

    @Autowired
    private RoleMainMenuPermissionRepository roleMainMenuPermissionRepository;

    public List<RoleFeaturePermission> getAllPermissions() {
        return permissionRepository.findAll();
    }

//    public List<RoleFeaturePermission> getPermissionsByRole(Long roleId) {
//        return permissionRepository.findByRoleId(roleId);
//    }

//    public List<FeaturePermissionDTO> getPermissionsByRole(Role role) {
//        List<RoleFeaturePermission> perms = roleFeaturePermissionRepository.findByRole(role);
//
//        return perms.stream().map(p -> {
//            FeaturePermissionDTO dto = new FeaturePermissionDTO();
//            dto.setRoleId(p.getRole().getId());
//            dto.setFeatureId(p.getFeature().getId());
//            dto.setFeatureCode(p.getFeature().getCode());
//            dto.setIsSearch(p.getIsSearch());
//            dto.setIsAdd(p.getIsAdd());
//            dto.setIsViewed(p.getIsViewed());
//            dto.setIsEdit(p.getIsEdit());
//            dto.setIsApprove(p.getIsApprove());
//            dto.setIsReject(p.getIsReject());
//            dto.setIsDeleted(p.getIsDeleted());
//            dto.setIsSave(p.getIsSave());
//            dto.setIsClear(p.getIsClear());
//            dto.setIsCancel(p.getIsCancel());
//            dto.setIsProcess(p.getIsProcess());
//            dto.setIsImport(p.getIsImport());
//            dto.setIsExport(p.getIsExport());
//            dto.setBStatus(p.getBStatus());
//            return dto;
//        }).collect(Collectors.toList());
//    }

    public RolePermissionsDTO getPermissionsByRole(Role role) {
        RolePermissionsDTO result = new RolePermissionsDTO();

        // --- Feature Permissions ---
        List<RoleFeaturePermission> perms = roleFeaturePermissionRepository.findByRole(role);
        List<FeaturePermissionDTO> featureDtos = perms.stream().map(p -> {
            FeaturePermissionDTO dto = new FeaturePermissionDTO();
            dto.setRoleId(p.getRole().getId());
            dto.setFeatureId(p.getFeature().getId());
            dto.setFeatureCode(p.getFeature().getCode());
            dto.setIsSearch(p.getIsSearch());
            dto.setIsAdd(p.getIsAdd());
            dto.setIsViewed(p.getIsViewed());
            dto.setIsEdit(p.getIsEdit());
            dto.setIsApprove(p.getIsApprove());
            dto.setIsReject(p.getIsReject());
            dto.setIsDeleted(p.getIsDeleted());
            dto.setIsSave(p.getIsSave());
            dto.setIsClear(p.getIsClear());
            dto.setIsCancel(p.getIsCancel());
            dto.setIsProcess(p.getIsProcess());
            dto.setIsImport(p.getIsImport());
            dto.setIsExport(p.getIsExport());
            dto.setBStatus(p.getBStatus());
            return dto;
        }).collect(Collectors.toList());
        result.setFeaturePermissions(featureDtos);

        // --- MainMenu Permissions ---
        List<RoleMainMenuPermission> menuPerms = roleMainMenuPermissionRepository.findByRole(role);
        List<MainMenuPermissionDTO> menuDtos = menuPerms.stream().map(mp -> {
            MainMenuPermissionDTO dto = new MainMenuPermissionDTO();
            dto.setRoleId(mp.getRole().getId());
            dto.setMainMenuId(mp.getMainMenu().getId());
            dto.setIsVisible(mp.getIsVisible());
            return dto;
        }).collect(Collectors.toList());
        result.setMainMenuPermissions(menuDtos);

        return result;
    }


    public RoleFeaturePermission createPermission(RoleFeaturePermission permission) {
        permission.setBStatus(true);
        permission.setCreatedAt(LocalDateTime.now());
        permission.setUpdatedAt(LocalDateTime.now());
        return permissionRepository.save(permission);
    }

    public RoleFeaturePermission updatePermission(Long id, RoleFeaturePermission updated) {
        return permissionRepository.findById(id)
                .map(permission -> {
                    copyPermissionFields(permission, updated);
                    permission.setUpdatedAt(LocalDateTime.now());
                    return permissionRepository.save(permission);
                })
                .orElseThrow(() -> new RuntimeException("Permission not found with ID " + id));
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    // NEW method to bulk save/update permissions
//    public void savePermissionsBulk(List<FeaturePermissionDTO> dtos) {
//        // --- Handle FeaturePermissionDTO ---
//        for (FeaturePermissionDTO dto : dtos) {
//            if (dto.getRoleId() == null || dto.getFeatureId() == null) {
//                throw new IllegalArgumentException("Role ID and Feature ID must not be null");
//            }
//
//            Role role = roleRepository.findById(dto.getRoleId())
//                    .orElseThrow(() -> new RuntimeException("Role not found: " + dto.getRoleId()));
//
//            Feature feature = featureRepository.findById(dto.getFeatureId())
//                    .orElseThrow(() -> new RuntimeException("Feature not found: " + dto.getFeatureId()));
//
//            Optional<RoleFeaturePermission> existingOpt = permissionRepository.findByRoleIdAndFeatureId(role.getId(), feature.getId());
//
//            RoleFeaturePermission permission = existingOpt.orElse(new RoleFeaturePermission());
//            permission.setRole(role);
//            permission.setFeature(feature);
//
//            permission.setIsSearch(dto.getIsSearch());
//            permission.setIsAdd(dto.getIsAdd());
//            permission.setIsViewed(dto.getIsViewed());
//            permission.setIsEdit(dto.getIsEdit());
//            permission.setIsApprove(dto.getIsApprove());
//            permission.setIsReject(dto.getIsReject());
//            permission.setIsDeleted(dto.getIsDeleted());
//            permission.setIsSave(dto.getIsSave());
//            permission.setIsClear(dto.getIsClear());
//            permission.setIsCancel(dto.getIsCancel());
//            permission.setIsProcess(dto.getIsProcess());
//            permission.setIsImport(dto.getIsImport());
//            permission.setIsExport(dto.getIsExport());
//            permission.setBStatus(true);
//
//            if (permission.getId() == null) {
//                permission.setCreatedAt(LocalDateTime.now());
//            }
//            permission.setUpdatedAt(LocalDateTime.now());
//            System.out.println("Saving permission: " + permission.getRole().getName() + " - " + permission.getFeature().getName());
//            permissionRepository.save(permission);
//        }
//
//    }

    public void savePermissionsBulk(List<FeaturePermissionDTO> featurePermissions,
                                    List<MainMenuPermissionDTO> mainMenuPermissions) {
        // --- Handle FeaturePermissionDTO ---
        for (FeaturePermissionDTO dto : featurePermissions) {
            if (dto.getRoleId() == null || dto.getFeatureId() == null) {
                throw new IllegalArgumentException("Role ID and Feature ID must not be null");
            }

            Role role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + dto.getRoleId()));

            Feature feature = featureRepository.findById(dto.getFeatureId())
                    .orElseThrow(() -> new RuntimeException("Feature not found: " + dto.getFeatureId()));

            Optional<RoleFeaturePermission> existingOpt =
                    permissionRepository.findByRoleIdAndFeatureId(role.getId(), feature.getId());

            RoleFeaturePermission permission = existingOpt.orElse(new RoleFeaturePermission());
            permission.setRole(role);
            permission.setFeature(feature);

            // set all flags
            permission.setIsSearch(dto.getIsSearch());
            permission.setIsAdd(dto.getIsAdd());
            permission.setIsViewed(dto.getIsViewed());
            permission.setIsEdit(dto.getIsEdit());
            permission.setIsApprove(dto.getIsApprove());
            permission.setIsReject(dto.getIsReject());
            permission.setIsDeleted(dto.getIsDeleted());
            permission.setIsSave(dto.getIsSave());
            permission.setIsClear(dto.getIsClear());
            permission.setIsCancel(dto.getIsCancel());
            permission.setIsProcess(dto.getIsProcess());
            permission.setIsImport(dto.getIsImport());
            permission.setIsExport(dto.getIsExport());
            permission.setBStatus(true);

            if (permission.getId() == null) {
                permission.setCreatedAt(LocalDateTime.now());
            }
            permission.setUpdatedAt(LocalDateTime.now());

            System.out.println("Saving feature permission: " +
                    permission.getRole().getName() + " - " + permission.getFeature().getName());

            permissionRepository.save(permission);
        }

        // --- Handle MainMenuPermissionDTO ---
        for (MainMenuPermissionDTO treeDto : mainMenuPermissions) {
            if (treeDto.getRoleId() == null || treeDto.getMainMenuId() == null) {
                throw new IllegalArgumentException("Role ID and MainMenu ID must not be null");
            }

            Role role = roleRepository.findById(treeDto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + treeDto.getRoleId()));

            MainMenu mainMenu = mainMenuRepository.findById(treeDto.getMainMenuId())
                    .orElseThrow(() -> new RuntimeException("MainMenu not found: " + treeDto.getMainMenuId()));

            Optional<RoleMainMenuPermission> existingOpt =
                    roleMainMenuPermissionRepository.findByRoleIdAndMainMenuId(role.getId(), mainMenu.getId());

            RoleMainMenuPermission menuPermission = existingOpt.orElse(new RoleMainMenuPermission());
            menuPermission.setRole(role);
            menuPermission.setMainMenu(mainMenu);
            menuPermission.setIsVisible(treeDto.getIsVisible());
            menuPermission.setBStatus(true);

            if (menuPermission.getId() == null) {
                menuPermission.setCreatedAt(LocalDateTime.now());
            }
            menuPermission.setUpdatedAt(LocalDateTime.now());

            System.out.println("Saving main menu permission: " +
                    menuPermission.getRole().getName() + " - " + menuPermission.getMainMenu().getName());
            System.out.println("Visible: " + treeDto.getIsVisible());

            roleMainMenuPermissionRepository.save(menuPermission);
        }
    }


    private void copyPermissionFields(RoleFeaturePermission target, RoleFeaturePermission source) {
        target.setIsSearch(source.getIsSearch());
        target.setIsAdd(source.getIsAdd());
        target.setIsViewed(source.getIsViewed());
        target.setIsEdit(source.getIsEdit());
        target.setIsApprove(source.getIsApprove());
        target.setIsReject(source.getIsReject());
        target.setIsDeleted(source.getIsDeleted());
        target.setIsSave(source.getIsSave());
        target.setIsClear(source.getIsClear());
        target.setIsCancel(source.getIsCancel());
        target.setIsProcess(source.getIsProcess());
        target.setIsImport(source.getIsImport());
        target.setIsExport(source.getIsExport());
        target.setBStatus(source.getBStatus());
    }

}
