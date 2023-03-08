package com.junior.cloud.mall.cartorder.controller;

import com.github.pagehelper.PageInfo;
import com.junior.cloud.mall.cartorder.model.request.CreateOrderReq;
import com.junior.cloud.mall.cartorder.model.vo.OrderVO;
import com.junior.cloud.mall.cartorder.service.OrderService;
import com.junior.cloud.mall.common.exception.ProductException;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/create")
    public ResponseUtils create(@Valid @RequestBody CreateOrderReq createOrderReq) throws ProductException {
        return ResponseUtils.success(orderService.create(createOrderReq));
    }

    @PostMapping("/detail")
    public ResponseUtils detail(String orderNo) throws ProductException {
        OrderVO orderVO = orderService.detail(orderNo);
        return ResponseUtils.success(orderVO);
    }

    @PostMapping("/cancel")
    public ResponseUtils cancel(String orderNo) throws ProductException {
        orderService.cancel(orderNo);
        return ResponseUtils.success();
    }

    @PostMapping("/list")
    public ResponseUtils listForCustomer(Integer pageNum, Integer pageSize) throws ProductException {
        PageInfo pageInfo = orderService.listForCustomer(pageNum, pageSize);
        return ResponseUtils.success(pageInfo);
    }

    @PostMapping("/qrcode")
    public ResponseUtils qrCode(String orderNo) {
        String qeCode = orderService.qrCode(orderNo);
        return ResponseUtils.success(qeCode);
    }

    @GetMapping("/pay")
    public ResponseUtils pay(String orderNo) throws ProductException {
        orderService.pay(orderNo);
        return ResponseUtils.success();
    }

    @PostMapping("/finish")
    public ResponseUtils finish(String orderNo) throws ProductException {
        orderService.finish(orderNo);
        return ResponseUtils.success();
    }
}
