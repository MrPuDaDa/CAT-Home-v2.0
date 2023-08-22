package org.jun.AlipayConfig;

import java.io.FileWriter;
import java.io.IOException;
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000123628345";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDawWh+wKugbQXU1P8c7soD+NfgLGHd66SXuiHqIvPMQf0RbhWwNXiFesZlN4r8fvkuaLu6Y+/FKx1o3yL9Ob8ev+tHk4CEaAwy9+aEHPhoX1vZqw+zdpSZ4VazfJdgQTW74P6NEccO/D2IFgGfeCn53tbyBaigEj3yUYnrMM4VCgxJ8QIVOTR5yH4clocJjXIbjqECVqieQCx37w0aX5QGXgcWmQAtX7Jt91n/At1N4udcoakaAPvZphK4YHuIL/FvAuRVqAgkBaoDh+KwoKzwdUdSlbNntqSi7lVn6FtMtu9V9FwoyCY+APpVWJ+sVOoyfbnihNHh0X/vMWUfzykXAgMBAAECggEBAMe55mo0B38Fi1lyzynfa1qNDKyEWSaBS1mse+KF3woTBbewRuUHLO7R1en7+E3osCGLRjs7m2LtpoBlmlCRNMoid/J13dUGGn0+kZz1xmelCPuHj2JPVgXZE4v0qVEcuK69EixCmvg3nQZu8TRkRg75Qk7ek8LyZNSlVcMCYGYbgWxLn2Z7SAGyG3Qcp+nEa0Z84sP5tJ5BVZv7p52gOx9uTKpOiWnte1p0ru6iTB/VadIcbdo4JbeTTffOG6me14ha3bm3RFTtI4wdwQkG3qp3WsssprV1X+abhpM7bN2Hiro9sMeZtaI3NHAvsrbDKQOYNggdgeSWVIKINHUu3UECgYEA/HiE09T/j/MlplQjpHjoMISw3To7jn6Ix205H1TXbOqZMHsHj8M0GcWwVdLKfUgidx/P169mWxVTFNfRgotmcnXV6wcpIdzENy2ITalVrAJl1ArEuhXVMQx9ihY3PuUyZWN6nGbhXaJguShDRAPYK9TFYhyrtb+YO/v5sYBKrT8CgYEA3dA8vlZKP8rBdDUeYPRdOKTHYAGwynGhTUQARXSV/o0SKAxH2j4FuAJp6/os3PEFUB4gJXvCn1Se0JTqr5JqlHRDLZ6zwRKo5baOLvx6ShWMrlzIW5fkM3JRCzEZar7hT0qxj2gLluBmvfhOE6JeqL1yjBq3MxdJiR5xbfuKFikCgYEAhXvK7pzdCeTKcShnN9xdFtnxEOZq+qnX82jCHNiklYdruwAxPUc90qp80sOCH771yfndz/GCsoGRzaFVuimt3rKCiaLdknPAolPGjxkKnMkfwX54CNLmRuqNrWaViwFvJb/04aBbY0Ao13x8JAfMUVBLk9YIyy0jdgOBPuHPmc0CgYAztLAEOn8yYaQ4340GUtBMPPUQ5zP77QMJzNk3V5cDcIUT+tDhCeYxX7arQHUmx7RSWcNqmW/jzkj7HEucw8e+dV+XidAjKKNgcO6x+z7SeDWEC7LUzxJt/67uqfJf1MEWx7WeVUWmVQb+L5ybrRTdxtOl3zBnY31LzmHonmMxCQKBgDfwGMaJyn+zDssvn8B5AGmiow47T9m9/rUnfvIijcAWwWi8N1KqQczQMqSlbuNM+fKxNmjczz5jvlJsk7nVUjpWWUZXBY4kb6m5jIMYyLKxq04x9FHPx99DBEZqoYmrEr58whUMhw2I1lC17gT1304KRWk61Y6hOlmWDm98VK0n";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlsgIbzz4RdPsIZWulz6ZzjbeJI50FPealQOsVKNkGdmP9n696IXjZ2zkLEBnfPrWq4L5ail0AMCaSP1Bx9h3tD2zhconBaShP18+EKpLE8V8o1o0pb2SVRT3NzVPaE9G+PvrKgngSIMiHXKmdPu9lOnHETWOHOrOy+DiUmF9yUzqSDAEqs2QBMcwv72JPOgq7otrNMm74K8BRy8SLO2JEM+GfdUFzyKXDEWY1udGQ8A1deVfI4yqukOkXNOWkkQJU8/D/2cgxf6jSaglCUFghj73/OSjzaYxEdPPMSv+D0uabdQ/jTTczJAjEx16zH3IRybWq/FFHMnZpdaUprFKxwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 目前没有
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 目前没有
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
