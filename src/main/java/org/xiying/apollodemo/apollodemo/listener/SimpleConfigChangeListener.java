package org.xiying.apollodemo.apollodemo.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

/**
 * 热部署 配置监听处理类
 *
 * @author shanghao5
 */
public class SimpleConfigChangeListener implements ConfigChangeListener {

    @Override
    public void onChange(ConfigChangeEvent configChangeEvent) {
        System.out.println(configChangeEvent.getNamespace());
        System.out.println(configChangeEvent.getChange("mq"));
        System.out.println("=====================================");
        Config config = ConfigService.getAppConfig();
        System.out.println("mq change:"+config.getProperty("mq",""));
        System.out.println("hello change:"+config.getProperty("hello",""));
    }
}
