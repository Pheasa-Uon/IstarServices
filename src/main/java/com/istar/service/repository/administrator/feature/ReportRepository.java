package com.istar.service.repository.administrator.feature;

import com.istar.service.entity.administrator.feature.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Reports, Long> {
    List<Reports> findByParentIsNull();
}
