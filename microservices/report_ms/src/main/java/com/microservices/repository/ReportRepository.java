package com.activedge.repository;


import com.activedge.model.Report;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report,Long> {
 Optional<Report> getById(Long id) ;
}
