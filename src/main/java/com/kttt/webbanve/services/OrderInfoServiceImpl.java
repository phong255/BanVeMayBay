package com.kttt.webbanve.services;

import com.kttt.webbanve.models.OrderInfo;
import com.kttt.webbanve.models.Ticket;
import com.kttt.webbanve.payload.CostStatistics;
import com.kttt.webbanve.payload.CostStatisticsByQuarter;
import com.kttt.webbanve.repositories.OrderInfoRepository;
import com.kttt.webbanve.repositories.OrderRepositories;
import com.kttt.webbanve.repositories.StatisticsRepoCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{

    private OrderRepositories orderRepositories;
    private StatisticsRepoCustomImpl srci;

    @Autowired
    public OrderInfoServiceImpl(StatisticsRepoCustomImpl srci, OrderRepositories orderRepositories) {
        this.orderRepositories = orderRepositories;
        this.srci = srci;
    }

//    @Override
//    public List<OrderInfoDto> findAll() {
//        List<OrderInfo> orderInfos = orderInfoRepository.findAll();
//        return orderInfos.stream().map(this::mapToDto).toList();
//    }

    @Override
    public Page<OrderInfo> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return orderRepositories.findAll(pageable);
    }

    @Override
    public OrderInfo getOrderByQrcode(String Qrcode) {
        return orderRepositories.getOrderInfoByQrCode(Qrcode);
    }

    @Override
    public OrderInfo getOrderByID(int orderID) {
        return orderRepositories.getOrderInfoByOrderID(orderID);
    }

    @Override
    public void saveOrder(OrderInfo orderInfo) {
        orderRepositories.save(orderInfo);
    }

    @Override
    public ArrayList<OrderInfo> getAllOrders() {
        return orderRepositories.getAllOrderInfos();
    }

    @Override
    public List<CostStatistics> statisticsCostByMonth() {
        return srci.statisticCostByMonth();
    }

    @Override
    public List<CostStatisticsByQuarter> costStatisticsByQuarter() {
        return srci.costStatisticsByQuarter();
    }


}
