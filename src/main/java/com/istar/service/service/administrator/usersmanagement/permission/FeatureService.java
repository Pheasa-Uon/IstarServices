package com.istar.service.service.administrator.usersmanagement.permission;

import com.istar.service.entity.administrator.usersmanagement.permission.Feature;
import com.istar.service.dto.administrator.usersmanagement.permission.FeatureTreeDTO;

import java.util.List;
import java.util.Optional;

public interface FeatureService {

    List<Feature> getAllFeatures();
    List<FeatureTreeDTO> getFeatureTree();
    Optional<Feature> getFeatureById(Long id);
    Feature createFeature(Feature feature);
    Feature updateFeature(Long id, Feature updated);
    void deleteFeature(Long id);

}

