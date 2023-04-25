package cn.itcast.user.web;

//import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import web.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//@Slf4j注解表示可以直接使用log变量
@Slf4j
@RestController
//@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private PatternProperties patternProperties;
//    @Value("${pattern.dateformat}")
//    private String dateformat;
//
//    @GetMapping("/now")
//    public String now(){
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
//    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Long id,@RequestHeader(required = false) String header) {
        User user = userService.queryById(id);
        System.out.println("now user------"+user);
        String msg = null!=user?"":"查询失败";
        System.out.println("header: "+header);
        return new Result(null!=user? Code.GET_OK:Code.GET_ERR,user,msg);
    }
}
