package today.belief.courier.autoconfigure;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author LXH
 * @Date 2019/12/18
 * @Description 多数据源动态切换注解注入接口
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchSource {

    String value();

}
