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


        return simpleFactory;
    }
}
