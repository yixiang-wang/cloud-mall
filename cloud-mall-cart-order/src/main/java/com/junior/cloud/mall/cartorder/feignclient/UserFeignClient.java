package com.junior.cloud.mall.cartorder.feignclient;

import com.junior.cloud.mall.user.model.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@FeignClient(value = "user-server")
public interface UserFeignClient {
    @GetMapping("/user/feign/getuser")
    User getUserForFeign();
}
