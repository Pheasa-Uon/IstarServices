package com.istar.corebanking.controller.administrator.feature;

import com.istar.corebanking.dto.administrator.feature.ReportTreeDTO;
import com.istar.corebanking.entity.administrator.feature.Reports;
import com.istar.corebanking.service.administrator.feature.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coregateways/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Reports>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reports> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/treetable")
    public ResponseEntity<List<ReportTreeDTO>> getReportTreetable() {
        return ResponseEntity.ok(reportService.getReportTree());
    }

}
