package today.belief.courier.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author LXH
 * @Date 2019/12/18
 * @Description 配置信息映射对象
 */
@Data
@ConfigurationProperties(prefix = "courier.pool")
public class CourierProperties {

    private Integer initialSize;
    private Integer maxActive;
    private Long maxWait;

    private List<Connection> connections;

    @Data
    public static class Connection {
        private String name;
        private String url;
        private String username;
        private String password;
        private String driver;

        private Integer initialSize;
        private Integer maxActive;
        private Long maxWait;
    }
}
