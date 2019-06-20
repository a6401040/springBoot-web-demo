package com.myself.dao;

import com.myself.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Product mapper for operate data of products table
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Mapper
public interface ProductDao {
    
    /**
      * select by id
      * @param id id
      * @return com.myself.model.entity.Product
      */
    Product select(@Param("id") long id);

    /**
      * update
      * @param product product
      * @return java.lang.Integer
      */
    Integer update(Product product);

    /**
     * insert
     * @param product product
     * @return Integer
     */
    Integer insert(Product product);

    /**
     * delete
     * @param productId productId
     * @return Integer
     */
    Integer delete(long productId);

    /**
     *  获取全部product
     * @return productList
     */
    List<Product> getAllProduct();
}
