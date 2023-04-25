package cn.itcast.feign.clients;


import cn.itcast.feign.config.UserFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.Result;

@FeignClient(value = "user-service",configuration = UserFeignConfig.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    Result findById(@PathVariable("id") Long id);
}
