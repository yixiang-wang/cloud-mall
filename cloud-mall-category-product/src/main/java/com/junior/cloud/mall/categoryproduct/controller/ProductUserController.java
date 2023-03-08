package com.junior.cloud.mall.categoryproduct.controller;

import com.junior.cloud.mall.categoryproduct.model.pojo.Product;
import com.junior.cloud.mall.categoryproduct.model.request.ListProductReq;
import com.junior.cloud.mall.categoryproduct.service.ProductService;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductUserController {
    @Resource
    private ProductService productService;

    @GetMapping("/detail")
    public ResponseUtils detail(Integer id) {
        return ResponseUtils.success(productService.getDetail(id));
    }

    @GetMapping("/list")
    public ResponseUtils list(ListProductReq productReq) {
        return ResponseUtils.success(productService.userList(productReq));
    }

    @GetMapping("/feign/detail")
    public Product detailForFeign(Integer id) {
        return productService.getDetail(id);
    }
}
