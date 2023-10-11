package com.kttt.webbanve.repositories;

import com.kttt.webbanve.models.PlaneFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlaneFlightRepositories extends PagingAndSortingRepository<PlaneFlight,Integer> {
    Page<PlaneFlight> findAllByFlightID(int fid, Pageable pageable);
}
