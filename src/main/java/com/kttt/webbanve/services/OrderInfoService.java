package com.kttt.webbanve.services;

import com.kttt.webbanve.models.OrderInfo;
import com.kttt.webbanve.payload.CostStatistics;
import com.kttt.webbanve.payload.CostStatisticsByQuarter;
import com.kttt.webbanve.payload.OrderInfoDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface OrderInfoService {
    Page<OrderInfo> findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    OrderInfo getOrderByQrcode(String Qrcode);
    OrderInfo getOrderByID(int orderID);
    void saveOrder(OrderInfo orderInfo);
    ArrayList<OrderInfo> getAllOrders();
//    List<OrderInfoDto> findAll();
    List<CostStatistics> statisticsCostByMonth();
    List<CostStatisticsByQuarter> costStatisticsByQuarter();
}
