package org.xiying.apollodemo.apollodemo.factory;

import org.springframework.beans.factory.annotation.Value;

public class SimpleFactory {

    @Value("${mq:mq}")
    private String mq;

    @Value("${hello:yes}")
    private String hello;

    public SimpleFactory() {
        System.out.println("ma+hello:"+mq+ "----" +hello);
    }

    public void getFactory(){
        System.out.println("ma+hello:"+mq+ "----" +hello);
    }
}
