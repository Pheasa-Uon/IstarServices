package com.istar.corebanking.service.administrator.usersmanagement.rolepermission;

import com.istar.corebanking.entity.administrator.feature.Feature;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.Role;
import com.istar.corebanking.entity.administrator.usersmanagement.rolepermission.RoleFeaturePermission;
import com.istar.corebanking.repository.administrator.feature.FeatureRepository;
import com.istar.corebanking.repository.administrator.usersmanagement.rolepermission.RoleFeaturePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@RequiredArgsConstructor
public class PermissionService {

    private final RoleFeaturePermissionRepository rfpRepo;
    private final FeatureRepository featureRepository;

    // Return merged permissions for a user's roles
    public Map<String, FeaturePermissionFlags> mergeByFeature(Set<Role> roles) {
        Map<String, FeaturePermissionFlags> map = new HashMap<>();

        // Convert role set to a list of IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleFeaturePermission entries
        for (RoleFeaturePermission rfp : rfpRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            String feature = rfp.getFeature().getCode();

            FeaturePermissionFlags flags = new FeaturePermissionFlags(
                    rfp.getIsSearch(),
                    rfp.getIsAdd(),
                    rfp.getIsViewed(),
                    rfp.getIsEdit(),
                    rfp.getIsApprove(),
                    rfp.getIsReject(),
                    rfp.getIsDeleted(),
                    rfp.getIsSave(),
                    rfp.getIsClear(),
                    rfp.getIsCancel(),
                    rfp.getIsProcess(),
                    rfp.getIsImport(),
                    rfp.getIsExport()
            );

            // Merge permissions for the same feature
            map.merge(feature, flags, FeaturePermissionFlags::or);
        }
        return map;
    }

    // Return all permissions (full access for admin)
    public Map<String, FeaturePermissionFlags> getAllPermissions() {
        Map<String, FeaturePermissionFlags> all = new HashMap<>();

        // Fetch all features from DB
        List<Feature> features = featureRepository.findAll();

        for (Feature feature : features) {
            // Here we read flags from Feature entity instead of hardcoding true
            all.put(feature.getCode(), new FeaturePermissionFlags(
                    feature.isSearch(),
                    feature.isAdd(),
                    feature.isViewed(),
                    feature.isEdit(),
                    feature.isApprove(),
                    feature.isReject(),
                    feature.isDeleted(),
                    feature.isSave(),
                    feature.isClear(),
                    feature.isCancel(),
                    feature.isProcess(),
                    feature.isImport(),
                    feature.isExport()
            ));
        }

        return all;
    }

//    public List<String> toAuthorities(Map<String, PermissionFlags> map) {
//        List<String> authorities = new ArrayList<>();
//        map.forEach((feature, flags) -> {
//            if (flags.search()) authorities.add("FEATURE" + feature + ".search");
//            if (flags.add()) authorities.add("FEATURE" + feature + ".add");
//            if (flags.view()) authorities.add("FEATURE" + feature + ".view");
//            if (flags.edit()) authorities.add("FEATURE" + feature + ".edit");
//            if (flags.approve()) authorities.add("FEATURE" + feature + ".approve");
//            if (flags.reject()) authorities.add("FEATURE" + feature + ".reject");
//            if (flags.deleted()) authorities.add("FEATURE" + feature + ".deleted");
//            if (flags.save()) authorities.add("FEATURE" + feature + ".save");
//            if (flags.clear()) authorities.add("FEATURE" + feature + ".clear");
//            if (flags.cancel()) authorities.add("FEATURE" + feature + ".cancel");
//            if (flags.process()) authorities.add("FEATURE" + feature + ".process");
//            if (flags.imported() ) authorities.add("FEATURE" + feature + ".imported");
//            if (flags.exported() ) authorities.add("FEATURE" + feature + ".exported");
//        });
//        return authorities;
//    }
}
