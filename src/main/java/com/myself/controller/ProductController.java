package com.myself.controller;

import com.myself.common.ReturnResponse;
import com.myself.model.entity.Product;
import com.myself.service.ProductService;
import com.myself.utils.ResponseUtil;
import com.myself.utils.ServiceException;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import com.terran4j.commons.api2doc.annotations.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Product controller
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Api2Doc(id = "demo", name = "产品接口", order = 1)
@ApiComment(seeClass = Product.class)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get product by id
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
    @Api2Doc(order = 1)
    @ApiComment("根据产品id，查询此产品的信息")
    @ApiError(value = "product.not.found", comment = "此产品不存在！")
    @GetMapping(name = "查询单个用户", value = "/{id}")
    public ReturnResponse getProduct(@PathVariable("id") Long productId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.getById(productId));
    }

    /**
     * Get all product
     *
     * @return
     * @throws ServiceException
     */
    @Api2Doc(order = 2)
    @ApiComment("查询所有产品")
    @GetMapping(name = "查询所有产品",value="")
    public ReturnResponse getAllProduct() {
        return ResponseUtil.generateResponse(productService.list());
    }

    /**
     * Update product by id
     *
     * @param productId
     * @param newProduct
     * @return
     * @throws ServiceException
     */
    @Api2Doc(order = 3)
    @ApiComment("根据产品id，更新指定的产品")
    @ApiError(value = "product.not.found", comment = "此产品不存在！")
    @PutMapping(name = "更新指定产品", value = "/{id}")
    public ReturnResponse updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct) throws ServiceException {
        newProduct.setId(productId);
        return ResponseUtil.generateResponse(productService.saveOrUpdate(newProduct));
    }

    /**
     * Delete product by id
     *
     * @param productId
     * @return
     * @throws ServiceException
     */
    @Api2Doc(order = 4)
    @ApiComment("根据产品id，删除指定的产品")
    @ApiError(value = "product.not.found", comment = "此产品不存在！")
    @DeleteMapping(name = "删除指定产品", value = "/{id}")
    public ReturnResponse deleteProduct(@PathVariable("id") long productId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.removeById(productId));
    }

    /**
     * Save product
     *
     * @param newProduct
     * @return
     * @throws ServiceException
     */
    @Api2Doc(order = 5)
    @ApiComment("添加一个新的产品。")
    @ApiError(value = "product.exists", comment = "此用户已经存在！")
    @PostMapping(name = "新增产品",value="")
    public ReturnResponse addProduct(@RequestBody Product newProduct) throws ServiceException {
        return ResponseUtil.generateResponse(productService.save(newProduct));
    }
}
