package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class UserFeignConfig {
    //feign4.0.2貌似不支持yml文件配置feign，所以使用配置类的方式实现覆写配置
    //自定义feign的配置

    //使用Slf4jLogger作为自定义的日志记录器
//    @Bean
//    public Logger myLogger(){
//        return new Slf4jLogger();
//    }

    //定义日志输出级别
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }
//
//    @Bean
//    public Retryer feignRetryer(){
//        return new Retryer.Default();
//    }
}
