package today.belief.courier.autoconfigure;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Author LXH
 * @Date 2019/12/18
 * @Description 切面注入方式
 */
@Order(0)
@Aspect
@Component
public class SwitchSourceAspect {

    @Pointcut("@annotation(today.belief.courier.autoconfigure.SwitchSource)")
    public void point() {

    }

    @Before("point()&&@annotation(source)")
    public void before(SwitchSource source) {
        RoutingContext.cut(source.value());
    }

    @After("point()")
    public void after() {
        RoutingContext.remove();
    }
}
