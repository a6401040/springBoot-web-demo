package com.myself.controller;

import com.myself.common.ReturnResponse;
import com.myself.model.entity.Product;
import com.myself.service.ProductService;
import com.myself.utils.ResponseUtil;
import com.myself.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Product controller
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
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
    @GetMapping("/{id}")
    public ReturnResponse getProduct(@PathVariable("id") Long productId) throws ServiceException {
        return ResponseUtil.generateResponse(productService.getById(productId));
    }

    /**
     * Get all product
     *
     * @return
     * @throws ServiceException
     */
    @GetMapping
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
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
    @PostMapping
    public ReturnResponse addProduct(@RequestBody Product newProduct) throws ServiceException {
        return ResponseUtil.generateResponse(productService.save(newProduct));
    }
}
