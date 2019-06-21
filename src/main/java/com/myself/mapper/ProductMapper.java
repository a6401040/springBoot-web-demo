package com.myself.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myself.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Kylin
 * @date 2019/6/21
 * @description: TODO
 * @version: V1.0
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
