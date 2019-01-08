/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: girl
 * Author:   user
 * Date:     2018/7/23 10:37
 * Description: girl属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wuhuanqi.girl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈girl属性〉
 *
 * @author user
 * @create 2018/7/23
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "girl")
public class girl {
    private Integer age;
    private String cupSize;
    public String getCupSize(){
        return cupSize;
    }
    public void setCupSize(String cupSize){
        this.cupSize=cupSize;
    }

}
