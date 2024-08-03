package com.yoda.springbootmall.dao.impl;

import org.springframework.stereotype.Component;

import com.yoda.springbootmall.dto.ProductRequest;
import com.yoda.springbootmall.model.Product;

@Component
public interface ProductDao {

	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
	
	Integer deleteProduct(Integer productId);
}
