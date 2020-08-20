package org.xiying.apollodemo.apollodemo.listener;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;
import org.xiying.apollodemo.apollodemo.controller.FakeController;

/**
 * 动态刷新日志级别示例
 *
 * @ClassName LogChangeListener
 * @Description
 * @Author shanghao5
 * @Date 2020/8/20 15:54
 **/
@Component
public class LogChangeListener {

    private static Logger logger = LoggerFactory.getLogger(FakeController.class);

    @Autowired
    private LoggingSystem loggingSystem;

    @ApolloConfigChangeListener("values.yaml")
    private void onChange(ConfigChangeEvent changeEvent) {
        ConfigChange change = changeEvent.getChange("logging.level.root");
        LogLevel level = LogLevel.valueOf(change.getNewValue().toUpperCase());
        loggingSystem.setLogLevel("root",level);

        // 检测是否生效
        logger.debug("[log]我是debug级别日志");
        logger.info("[log]我是info级别日志");
        logger.warn("[log]我是warn级别日志");
        logger.error("[log]我是error级别日志");
    }

}
