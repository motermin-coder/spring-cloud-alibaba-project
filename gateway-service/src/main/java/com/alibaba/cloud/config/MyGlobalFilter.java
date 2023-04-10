package com.alibaba.cloud.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Auth tom
 * @Date 2023-04-10 23:11:01
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 判断是否有请求头
        String authortion = exchange.getRequest().getHeaders().get("Authorization").get(0);
        if (authortion == null || "".equals(authortion)){
            System.out.println("--------当前用户未授权------");
            // 没有请求头，则设置响应的状态码，并让响应完成
            exchange.getResponse().setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
