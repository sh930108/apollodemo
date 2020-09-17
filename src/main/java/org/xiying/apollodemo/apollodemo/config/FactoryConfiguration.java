package org.xiying.apollodemo.apollodemo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xiying.apollodemo.apollodemo.factory.SimpleFactory;
import org.xiying.apollodemo.apollodemo.listener.SimpleConfigChangeListener;

/**
 * 配置类
 *
 * @author shanghao5
 */
@Configuration
public class FactoryConfiguration {

    @Value("${middleware.consul.host:yes}")
    private String key1;
    @Value("${feign.client.url:yes}")
    private String key2;
    @Value("${amq.server:yes}")
    private String key3;

    @Bean
    public SimpleFactory getSimpleFactory(){
        Config config = ConfigService.getConfig("values.yaml");
        config.addChangeListener(new SimpleConfigChangeListener());

        // 使用configService 获取
        String s = config.getProperty("feign.client.url", "127.0.0.1");
        System.out.println(s);

        // 环境变量方式获取
        SimpleFactory simpleFactory = SimpleFactory.builder()
                .host(key1)
                .url(key2)
                .build();
        System.out.println("[factory]:"+new Gson().toJson(simpleFactory));
        System.out.println("amq.server:"+key3);
        return simpleFactory;
    }


}
