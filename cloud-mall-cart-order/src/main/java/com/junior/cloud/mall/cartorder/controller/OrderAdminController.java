package com.junior.cloud.mall.cartorder.controller;

import com.github.pagehelper.PageInfo;
import com.junior.cloud.mall.cartorder.service.OrderService;
import com.junior.cloud.mall.common.exception.ProductException;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/admin/order")
public class OrderAdminController {
    @Resource
    OrderService orderService;

    @GetMapping("/list")
    public ResponseUtils listForAdmin(Integer pageNum, Integer pageSize) throws ProductException {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ResponseUtils.success(pageInfo);
    }

    @PostMapping("/delivered")
    public ResponseUtils delivered(String orderNo) throws ProductException {
        orderService.delivered(orderNo);
        return ResponseUtils.success();
    }

    @GetMapping("/statistics")
    public ResponseUtils statistics(@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate){
        return ResponseUtils.success(orderService.OrderStatistics(startDate,endDate));
    }
}
