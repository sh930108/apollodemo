package org.xiying.apollodemo.apollodemo.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * feign url 配置在 Apollo 上，从Apollo 获取
 *
 * @ClassName FakeFiegnClient
 * @Description
 * @Author shanghao5
 * @Date 2020/8/20 14:35
 **/
@FeignClient(name = "data", url = "${feign.client.url}")
public interface FakeFeignClient {

    /**
     * 假的feign接口调用，测试配置
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> fakeRequestMethod();
}
