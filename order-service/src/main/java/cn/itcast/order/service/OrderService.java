package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Result;

@Service
public class OrderService {

//    public static final String URL_PREFIX = "http://user-service/user/";
    @Autowired
    private OrderMapper orderMapper;

//    @Autowired(required = false)
//    @Resource
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId){
        Order order = orderMapper.findById(orderId);
        if(null!=order && null!=order.getUserId()){
            Result result = userClient.findById(order.getUserId());
            //类型解析
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.convertValue(result.getData(),User.class);
            order.setUser(user);
        }
        return order;
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //2.请求user模块的数据
////        String url= "http://localhost:8081/user/"+order.getUserId();
//        //使用eureka注册服务的服务名代替ip和端口号
//        if (null!=order && null!= order.getUserId()){
//            String url = URL_PREFIX+order.getUserId();
//            //todo 注意：这里拿到的不是User类型而是Result类型，所以需要提取一下
//            Result result = restTemplate.getForObject(url, Result.class);
//            //3.设置给order
//            assert result != null;
//            //将hashmap转成json再转成实体类对象
//            ObjectMapper mapper = new ObjectMapper();
//            User user = mapper.convertValue(result.getData(),User.class);
//            order.setUser(user);
//            }
//        // 4.返回
//        return order;
//    }
}
