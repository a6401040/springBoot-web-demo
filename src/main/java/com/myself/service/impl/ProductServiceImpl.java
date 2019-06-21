package com.myself.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.mapper.ProductMapper;
import com.myself.model.entity.Product;
import com.myself.service.ProductService;
import org.springframework.stereotype.Service;



/**
 *  Product service for handler logic of product operation
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Service(value = "productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


}
