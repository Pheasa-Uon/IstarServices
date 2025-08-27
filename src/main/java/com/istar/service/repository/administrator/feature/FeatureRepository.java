package com.istar.service.repository.administrator.feature;

import com.istar.service.entity.administrator.feature.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByParentIsNull(); // For top-level menus
    Optional<Feature> findByCode(String Code);
}

