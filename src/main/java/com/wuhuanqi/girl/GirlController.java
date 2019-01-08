/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: GirlController
 * Author:   user
 * Date:     2018/7/24 8:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wuhuanqi.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author user
 * @create 2018/7/24
 * @since 1.0.0
 */
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;


    @GetMapping(value="/girls")
    public List<Girl01> girlList(HttpSession session){
        System.out.println(session.getAttribute("username"));
        session.setAttribute("username","TOM");
        return girlRepository.findAll();

    }
    @PostMapping(value="/login")
    public String login(@RequestParam("username") String username,HttpSession session){

        System.out.println(session.getAttribute("username"));
        session.setAttribute("username",username);
        return "lala";

    }
    /**
     * 功能描述: <br>
     * 〈添加一个女生〉
     *

     * @return:Girl01
     * @since: 1.0.0
     * @Author:
     * @Date:
     */

    @PostMapping(value = "/girls")
    public Girl01 girlAdd(@RequestParam("id") Integer id,  @RequestParam("age") Integer age,
                          HttpSession session){
        Girl01 girl01=new Girl01();
        girl01.setId(id);
        girl01.setAge(age);
        return girlRepository.save(girl01);
    }

}
