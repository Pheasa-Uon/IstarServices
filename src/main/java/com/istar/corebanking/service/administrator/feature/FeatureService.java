package com.istar.corebanking.service.administrator.feature;

import com.istar.corebanking.entity.administrator.feature.Feature;
import com.istar.corebanking.dto.administrator.feature.FeatureTreeDTO;

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

