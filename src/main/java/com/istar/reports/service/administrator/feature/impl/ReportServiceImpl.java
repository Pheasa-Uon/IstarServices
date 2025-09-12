package com.istar.reports.service.administrator.feature.impl;

import com.istar.reports.dto.administrator.feature.ReportTreeDTO;
import com.istar.reports.entity.administrator.feature.Reports;
import com.istar.reports.repository.administrator.feature.ReportRepository;
import com.istar.reports.service.administrator.feature.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<ReportTreeDTO>  getReportTree() {
        List<Reports> report = reportRepository.findByParentIsNull();
        return report.stream().map(this::convertToTreeReport).collect(Collectors.toList());
    }

    private ReportTreeDTO convertToTreeReport(Reports report) {

        ReportTreeDTO dto = new ReportTreeDTO();
        dto.setId(report.getId());
        dto.setCode(report.getCode());
        dto.setName(report.getName());
        dto.setRoutePath(report.getRoutePath());
        dto.setbStatus(report.getBStatus());
        dto.setDisplayOrder(report.getOrders());
        dto.setDescription(report.getDescription());

        if (report.getChildren() != null && !report.getChildren().isEmpty()){
            List<ReportTreeDTO> children = report.getChildren().stream()
                    .map(this::convertToTreeReport).collect(Collectors.toList());
            dto.setChildren(children);
        }
        return dto;
    }

    public List<Reports>  getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Reports> getReportById(Long id) {
        return reportRepository.findById(id);
    }
}
