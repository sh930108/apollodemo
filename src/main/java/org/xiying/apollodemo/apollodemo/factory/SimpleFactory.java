package org.xiying.apollodemo.apollodemo.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 示例简单实体
 *
 * @author shanghao5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleFactory {
    private String host;
    private String url;
    private String port;
}
