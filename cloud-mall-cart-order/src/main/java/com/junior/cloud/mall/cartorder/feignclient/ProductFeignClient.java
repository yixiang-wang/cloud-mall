package com.junior.cloud.mall.cartorder.feignclient;

import com.junior.cloud.mall.categoryproduct.model.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "category-product-server")
public interface ProductFeignClient {

    @GetMapping("/product/feign/detail")
    Product detailForFeign(@RequestParam Integer id);

    @PostMapping("/feign/product/update")
    void updateForFeign(@RequestParam Integer productId,@RequestParam Integer stock);
}
