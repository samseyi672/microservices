package com.activedge.controller;


import com.activedge.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    ReportService reportService ;

    @Value("${api.version}")
    private String apiVersion ;

    @GetMapping("/reportconnection/{id}")
    public ResponseEntity<?> reportConnection(Long id){
        return reportService.findById(id)
                .map(report -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(apiVersion)
                                .location(new URI("/report/" + report.getId()))
                                .body(report);
                    } catch (URISyntaxException e ) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
