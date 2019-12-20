package today.belief.courier.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import today.belief.courier.properties.CourierProperties;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author LXH
 * @Date 2019/12/18
 * @Description 动态多数据源配置
 */
@Configuration
@ConditionalOnClass(CourierDataSource.class)
@EnableConfigurationProperties(CourierProperties.class)
public class CourierAutoConfiguration {

    /**
     * 连接池信息，通过配置文件映射
     */
    @Resource
    private CourierProperties pool;

    @Bean
    public DataSource courierDataSource() {
        return new CourierDataSource(pool);
    }


}
