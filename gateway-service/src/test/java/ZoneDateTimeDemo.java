import java.time.ZonedDateTime;

/**
 * @Auth tom
 * @Date 2023-04-10 23:23:01
 */
public class ZoneDateTimeDemo {

    public static void main(String[] args) {
        // 获取断言请求头的时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("now = " + now); // 2023-04-10T23:25:44.562+08:00[Asia/Shanghai]
    }
}
