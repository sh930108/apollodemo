package org.xiying.apollodemo.apollodemo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xiying.apollodemo.apollodemo.factory.SimpleFactory;
import org.xiying.apollodemo.apollodemo.listener.SimpleConfigChangeListener;

@Configuration
@EnableApolloConfig
public class FactoryConfiguration {

    private Config config;
    @Value("${hello:yes}")
    private String helloAtValue;
    @Value("${spring.application.name:yes}")
    private String key1;
    @Value("${test.hello:yes}")
    private String key2;
    @Value("${amq.ip:yes}")
    private String key3;
    @Value("${management.health.status.order:yes}")
    private String key4;
    @Value("${test:yes}")
    private String key5;
    @Value("${middleware.postgresql.port:yes}")
    private String key6;
    @Value("${middleware.postgresql.host:yes}")
    private String key7;
    @Value("${middleware.consul.host:yes}")
    private String key8;
    @Value("${middleware.consul.port:yes}")
    private String key9;


    @Bean
    public SimpleFactory getSimpleFactory(){
        config = ConfigService.getAppConfig();
        config.addChangeListener(new SimpleConfigChangeListener());
        String hello = config.getProperty("hello", "");
        String mq = config.getProperty("mq", "");
        System.out.println("hello:"+hello);
        System.out.println("mq:"+mq);
        System.out.println("==================================");
        SimpleFactory simpleFactory = new SimpleFactory();
        System.out.println("==================================");
        System.out.println("helloAtValue:"+helloAtValue);
        System.out.println("==================================");

        System.out.println("==================================");
        System.out.println("key1:"+key1);
        System.out.println("key2:"+key2);
        System.out.println("key3:"+key3);
        System.out.println("key4:"+key4);
        System.out.println("key5:"+key5);
        System.out.println("==================================");
        System.out.println("key6:"+key6);
        System.out.println("key7:"+key7);
        System.out.println("key8:"+key8);
        System.out.println("key9:"+key9);
        System.out.println("==================================");
        return simpleFactory;
    }



}
