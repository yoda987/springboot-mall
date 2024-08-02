package com.yoda.springbootmall.dao.impl;

import org.springframework.stereotype.Component;

import com.yoda.springbootmall.model.Product;

@Component
public interface ProductDao {

	Product getProductById(Integer productId);
	
}
