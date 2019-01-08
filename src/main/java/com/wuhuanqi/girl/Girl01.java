/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Girl01
 * Author:   user
 * Date:     2018/7/23 15:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wuhuanqi.girl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author user
 * @create 2018/7/23
 * @since 1.0.0
 */
@Entity
public class Girl01 {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer age;
    private String cupSize;
    public Girl01(){

    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }
}
