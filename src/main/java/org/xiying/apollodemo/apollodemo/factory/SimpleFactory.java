package org.xiying.apollodemo.apollodemo.factory;

import org.springframework.beans.factory.annotation.Value;

public class SimpleFactory {

    @Value("${mq:mq}")
    private String mq;

    @Value("${hello:yes}")
    private String hello;

    @Value("${keytest:yes}")
    private String keytest;

    public SimpleFactory() {
        System.out.println("mq+hello+keytest:"+mq+ "----" +hello+ "----" +keytest);
    }

    public void getFactory(){
        System.out.println("ma+hello:"+mq+ "----" +hello);
    }
}
