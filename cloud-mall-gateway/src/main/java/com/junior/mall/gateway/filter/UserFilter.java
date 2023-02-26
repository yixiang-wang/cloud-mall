package com.junior.mall.gateway.filter;

import com.junior.cloud.mall.common.exception.MallExceptionEnum;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import com.junior.cloud.mall.user.model.pojo.User;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UserFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path=exchange.getRequest().getURI().getPath();
        if(path.contains("user")){
            // 判断是否登录
            AtomicBoolean isLogin = new AtomicBoolean(false);
            exchange.getSession().subscribe(webSession -> {
                User user=webSession.getAttribute("user");
                if (user!=null){
                    isLogin.set(true);
                }
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!isLogin.get()){
                ServerHttpResponse response=exchange.getResponse();
                response.setStatusCode(HttpStatus.ACCEPTED);
                byte[] bytes=ResponseUtils.error(MallExceptionEnum.NEED_LOGIN).toJsonString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bytes);
                return response.writeWith(Mono.just(buffer));
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
