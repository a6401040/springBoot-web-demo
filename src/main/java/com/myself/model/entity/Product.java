package com.myself.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Product bean
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Data
@TableName("t_product")
@Accessors(chain = true)
public class Product{

    @ApiComment(value = "用户id", sample = "99")
    private long id;
    @ApiComment(value = "用户名", sample = "terran4j")
    private String name;
    @ApiComment(value = "价格", sample = "22")
    private long price;


}
