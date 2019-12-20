package today.belief.courier.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;
import today.belief.courier.properties.CourierProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author LXH
 * @Date 2019/12/18
 * @Description 多数据源动态切换实现方式
 */
public class CourierDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return RoutingContext.current();
    }

    CourierDataSource(CourierProperties courier) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        init(targetDataSources, courier);
        setDefaultTargetDataSource(targetDataSources.get(courier.getConnections().get(0).getName()));
        setTargetDataSources(targetDataSources);
        afterPropertiesSet();
    }

    /**
     * 读取配置文件中的属性，设置到数据源中
     *
     * @param dataSources 把设置好的数据源添加到此参数中
     * @param courier     连接信息
     */
    private void init(Map<Object, Object> dataSources, CourierProperties courier) {
        DruidDataSource dataSource;
        for (CourierProperties.Connection connection : courier.getConnections()) {
            dataSource = new DruidDataSource();
            dataSource.setInitialSize(courier.getInitialSize());
            dataSource.setMaxActive(courier.getMaxActive());
            dataSource.setMaxWait(courier.getMaxWait());

            datasource(connection, dataSource);

            dataSources.put(connection.getName(), dataSource);
        }
    }

    /**
     * 设置单个数据源的配置
     *
     * @param connection 连接信息对象
     * @param dataSource 数据源对象
     */
    private void datasource(CourierProperties.Connection connection, DruidDataSource dataSource) {
        dataSource.setName(connection.getName());
        dataSource.setUrl(connection.getUrl());
        dataSource.setUsername(connection.getUsername());
        dataSource.setPassword(connection.getPassword());
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");

        if (!StringUtils.isEmpty(connection.getDriver())) {
            dataSource.setDriverClassName(connection.getDriver());
        }

        if (connection.getInitialSize() != null) {
            dataSource.setInitialSize(connection.getInitialSize());
        }

        if (connection.getMaxActive() != null) {
            dataSource.setMaxActive(connection.getMaxActive());
        }

        if (connection.getMaxWait() != null) {
            dataSource.setMaxWait(connection.getMaxWait());
        }
    }
}
