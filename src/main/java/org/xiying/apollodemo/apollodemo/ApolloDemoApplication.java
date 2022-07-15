package org.xiying.apollodemo.apollodemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 支持 apollo 注解
 *
 * @author shanghao5
 */
@SpringBootApplication
@EnableFeignClients
@EnableApolloConfig
public class ApolloDemoApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApolloDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApolloDemoApplication.class);
        application.setRegisterShutdownHook(true);
        application.run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("******************************demo shutdown******************************");
        }));
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("******************************demo startup******************************");
    }

}
