package com.kttt.webbanve.services;

import com.kttt.webbanve.payload.CostStatistics;
import com.kttt.webbanve.payload.CostStatisticsByQuarter;
import com.kttt.webbanve.payload.OrderInfoDto;

import java.util.List;

public interface OrderInfoService {

//    List<OrderInfoDto> findAll();
    List<CostStatistics> statisticsCostByMonth();
    List<CostStatisticsByQuarter> costStatisticsByQuarter();
}
