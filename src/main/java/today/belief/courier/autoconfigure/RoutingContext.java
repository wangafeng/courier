package today.belief.courier.autoconfigure;

/**
 * @Author LXH
 * @Date 2019/12/18
 * @Description 多数据源动态切换的实现
 */
class RoutingContext {

    private static final ThreadLocal<String> NAMES = new ThreadLocal<>();

    /**
     * 获取数据源的名称
     *
     * @return 当前数据源的名称
     */
    static String current() {
        return NAMES.get();
    }

    /**
     * 切换数据源
     *
     * @param name 数据源的名称
     */
    static void cut(String name) {
        NAMES.set(name);
    }

    /**
     * 清除当前数据源
     */
    static void remove() {
        NAMES.remove();
    }
}
