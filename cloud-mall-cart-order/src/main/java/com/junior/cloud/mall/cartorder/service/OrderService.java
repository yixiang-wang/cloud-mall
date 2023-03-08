package com.junior.cloud.mall.cartorder.service;

import com.github.pagehelper.PageInfo;
import com.junior.cloud.mall.cartorder.model.request.CreateOrderReq;
import com.junior.cloud.mall.cartorder.model.vo.OrderStatisticsVO;
import com.junior.cloud.mall.cartorder.model.vo.OrderVO;
import com.junior.cloud.mall.common.exception.ProductException;


import java.util.Date;
import java.util.List;

public interface OrderService {
    String create(CreateOrderReq createOrderReq) throws ProductException;

    OrderVO detail(String orderNo) throws ProductException;

    void cancel(String orderNo) throws ProductException;

    PageInfo listForCustomer(Integer pageNum, Integer pageSize) throws ProductException;

    String qrCode(String orderNo);

    void pay(String orderNo) throws ProductException;

    PageInfo listForAdmin(Integer pageNum, Integer pageSize) throws ProductException;

    void delivered(String orderNo) throws ProductException;

    void finish(String orderNo) throws ProductException;

    List<OrderStatisticsVO> OrderStatistics(Date startDate, Date endDate);
}
