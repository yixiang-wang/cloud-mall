package com.junior.cloud.mall.cartorder.controller;

import com.junior.cloud.mall.cartorder.feignclient.UserFeignClient;
import com.junior.cloud.mall.cartorder.model.vo.CartVO;
import com.junior.cloud.mall.cartorder.service.CartService;
import com.junior.cloud.mall.common.exception.ProductException;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("/list")
    public ResponseUtils getList() {
        List<CartVO> cartList = cartService.getList(userFeignClient.getUserForFeign().getId());
        return ResponseUtils.success(cartList);
    }

    @PostMapping("/add")
    public ResponseUtils add(Integer productId, Integer count) throws ProductException {
        List<CartVO> cartVOS = cartService.add(userFeignClient.getUserForFeign().getId(), productId, count);
        return ResponseUtils.success(cartVOS);
    }

    @PostMapping("/update")
    public ResponseUtils update(Integer productId, Integer count) throws ProductException {
        List<CartVO> cartVOS = cartService.update(userFeignClient.getUserForFeign().getId(), productId, count);
        return ResponseUtils.success(cartVOS);
    }

    @PostMapping("/delete")
    public ResponseUtils delete(Integer productId) throws ProductException {
        List<CartVO> cartVOS = cartService.delete(userFeignClient.getUserForFeign().getId(), productId);
        return ResponseUtils.success(cartVOS);
    }

    @PostMapping("/select")
    public ResponseUtils select(Integer productId, Integer selected) throws ProductException {
        List<CartVO> cartVOS = cartService.selectOrNot(userFeignClient.getUserForFeign().getId(), productId, selected);
        return ResponseUtils.success(cartVOS);
    }

    @PostMapping("/selectAll")
    public ResponseUtils selectAll(Integer selected) {
        List<CartVO> cartVOS = cartService.selectAllOrNot(userFeignClient.getUserForFeign().getId(), selected);
        return ResponseUtils.success(cartVOS);
    }
}
