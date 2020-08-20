package org.xiying.apollodemo.apollodemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiying.apollodemo.apollodemo.microservice.FakeFeignClient;

/**
 * @ClassName FakeController
 * @Description
 * @Author shanghao5
 * @Date 2020/8/20 14:46
 **/
@RestController
public class FakeController {

    private static Logger logger = LoggerFactory.getLogger(FakeController.class);

    @Autowired
    private FakeFeignClient fakeFeignClient;

    @GetMapping(path = "/hello")
    void hello() {
        String body = fakeFeignClient.fakeRequestMethod().getBody();
        System.out.println("===============");
        System.out.println(body);
        System.out.println("===============");
    }

    @GetMapping(path = "/test")
    String test() {
        logger.debug("[log]我是debug级别日志");
        logger.info("[log]我是info级别日志");
        logger.warn("[log]我是warn级别日志");
        logger.error("[log]我是error级别日志");
        return "hello";
    }
}
