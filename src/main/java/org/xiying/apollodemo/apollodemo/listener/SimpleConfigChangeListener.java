package org.xiying.apollodemo.apollodemo.listener;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

/**
 * 热部署 配置监听处理类
 *
 * @author shanghao5
 */
public class SimpleConfigChangeListener implements ConfigChangeListener {

    @Override
    public void onChange(ConfigChangeEvent configChangeEvent) {
        String namespace = configChangeEvent.getNamespace();
        ConfigChange change = configChangeEvent.getChange("logging.level.root");
        System.out.println("namespace:"+namespace);
        System.out.println("new value"+ change.getNewValue());
        System.out.println("old value"+ change.getOldValue());
    }
}
