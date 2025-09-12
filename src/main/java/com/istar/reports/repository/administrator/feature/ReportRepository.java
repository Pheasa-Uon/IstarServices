package com.istar.reports.repository.administrator.feature;

import com.istar.reports.entity.administrator.feature.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Reports, Long> {
    List<Reports> findByParentIsNull();

    List<Reports> id(Long id);
}
