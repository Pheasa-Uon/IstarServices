package com.istar.service.service.administrator.usersmanagement.permission;

import com.istar.service.entity.administrator.usersmanagement.permission.Role;
import com.istar.service.entity.administrator.usersmanagement.permission.RoleFeaturePermission;
import com.istar.service.repository.administrator.usersmanagement.permission.RoleFeaturePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@RequiredArgsConstructor
public class PermissionService {

    private final RoleFeaturePermissionRepository rfpRepo;

    public Map<String, PermissionFlags> mergeByFeature(Set<Role> roles) {
        Map<String, PermissionFlags> map = new HashMap<>();

        // Convert role set to a list of IDs
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .toList();

        // Fetch RoleFeaturePermission entries
        for (RoleFeaturePermission rfp : rfpRepo.findByRoleIdInAndBStatusIsTrue(roleIds)) {
            String feature = rfp.getFeature().getCode();

            PermissionFlags flags = new PermissionFlags(
                    rfp.getIsSearch(),
                    rfp.getIsViewed(),
                    rfp.getIsAdd(),
                    rfp.getIsEdit(),
                    rfp.getIsDeleted()
            );

            // Merge permissions for the same feature
            map.merge(feature, flags, PermissionFlags::or);
        }

        return map;
    }

    public List<String> toAuthorities(Map<String, PermissionFlags> map) {
        List<String> authorities = new ArrayList<>();
        map.forEach((feature, flags) -> {
            if (flags.search()) authorities.add("FEATURE" + feature + ".search");
            if (flags.view()) authorities.add("FEATURE" + feature + ".view");
            if (flags.add()) authorities.add("FEATURE" + feature + ".add");
            if (flags.edit()) authorities.add("FEATURE" + feature + ".edit");
            if (flags.delete()) authorities.add("FEATURE" + feature + ".delete");
        });
        return authorities;
    }
}
