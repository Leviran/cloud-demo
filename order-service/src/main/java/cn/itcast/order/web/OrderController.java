package cn.itcast.order.web;

import cn.itcast.feign.pojo.User;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import feign.Feign;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.*;

@RestController
// spring-cloud原生注解refreshscope实现配置自动更新
//@RefreshScope
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Result queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
        String msg = null!=order?"":"查询失败";
        return new Result(null!=order? Code.GET_OK:Code.GET_ERR,order,msg);
    }

    void test(){
        User user = Feign.builder()
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(User.class,"");
    }
}
