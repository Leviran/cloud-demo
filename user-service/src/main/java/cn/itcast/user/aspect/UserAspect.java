package cn.itcast.user.aspect;


//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;

//@Component
//@Aspect
//public class UserAspect {
//    @Pointcut("execution(* cn.itcast.user.mapper.find*(..))")
//    private void pt(){}
//
//
//    @Around("pt()")
//    public Object after(ProceedingJoinPoint pjp) throws Throwable{
//        System.out.println("before around...");
//        Object obj = pjp.proceed();
//        System.out.println(obj);
//        System.out.println("after around...");
//        return obj;
//    }
//}
