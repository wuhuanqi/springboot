/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloController
 * Author:   user
 * Date:     2018/7/23 10:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wuhuanqi.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 五环旗
 * @create 2018/7/23
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private girl girl1;
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String sayhello(){
        return girl1.getCupSize();

    }

}
