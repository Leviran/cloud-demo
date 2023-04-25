package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        //2.获取参数并判断
        String auth = params.getFirst("authorization");
        if("admin".equals(auth)){
            //3.是，放行
            return chain.filter(exchange);
        }
        //4.否，拦截（直接拦截用户体验不好，所以设置一下状态码）
        //4.1 设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //4.2拦截请求
        return exchange.getResponse().setComplete();
    }


    @Override
    public int getOrder() {
        //当有多个拦截器时，需要根据order的value判断拦截器的执行顺序，数字越小优先级越大
        //也可以直接通过添加order注解的方式设置value
        return -1;
    }
}
