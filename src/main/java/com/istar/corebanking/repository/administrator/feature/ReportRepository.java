package com.istar.corebanking.repository.administrator.feature;

import com.istar.corebanking.entity.administrator.feature.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Reports, Long> {
    List<Reports> findByParentIsNull();
}
