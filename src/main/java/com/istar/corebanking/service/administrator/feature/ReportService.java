package com.istar.corebanking.service.administrator.feature;

import com.istar.corebanking.dto.administrator.feature.ReportTreeDTO;
import com.istar.corebanking.entity.administrator.feature.Reports;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    List<ReportTreeDTO> getReportTree();
    List<Reports> getAllReports();
    Optional<Reports> getReportById(Long id);
}
