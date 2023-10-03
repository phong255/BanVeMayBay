package com.kttt.webbanve.repositories;

import com.kttt.webbanve.models.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositories extends JpaRepository<OrderInfo,Integer> {
    public OrderInfo getOrderInfoByQrCode(String qrCode);
}
