package org.xiying.apollodemo.apollodemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApolloDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApolloDemoApplication.class);
        application.setRegisterShutdownHook(true);
        application.run(args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //logger.info("******************************demo shutdown******************************");
        }));
    }

    @Override
    public void run(String... args) throws Exception {
        //logger.info("******************************demo startup******************************");
    }

}
