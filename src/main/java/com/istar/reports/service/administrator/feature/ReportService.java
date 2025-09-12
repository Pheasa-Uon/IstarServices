package com.istar.reports.service.administrator.feature;

import com.istar.reports.dto.administrator.feature.ReportTreeDTO;
import com.istar.reports.entity.administrator.feature.Reports;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    List<ReportTreeDTO> getReportTree();
    List<Reports> getAllReports();
    Optional<Reports> getReportById(Long id);
}
