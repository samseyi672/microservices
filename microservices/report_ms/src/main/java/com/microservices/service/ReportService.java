package com.activedge.service;


import com.activedge.model.Report;
import com.activedge.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReportService {

    @Autowired
    ReportRepository repository ;

    public Optional<Report> findById(Long id){
         return repository.getById(id);
    }
}
