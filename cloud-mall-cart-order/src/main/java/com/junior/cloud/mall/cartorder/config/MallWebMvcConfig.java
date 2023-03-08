package com.junior.cloud.mall.cartorder.config;

import com.junior.cloud.mall.cartorder.common.OrderConstant;
import com.junior.cloud.mall.categoryproduct.common.ProductConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述：     配置地址映射
 */
@Configuration
public class MallWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations(
                "file:" + OrderConstant.FILE_UPLOAD_DIR);
    }
}
