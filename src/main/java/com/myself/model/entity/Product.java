package com.myself.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Product bean
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Data
@TableName("t_product")
public class Product{

    private long id;
    private String name;
    private long price;


}
