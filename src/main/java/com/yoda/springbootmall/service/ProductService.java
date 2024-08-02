package com.yoda.springbootmall.service;

import org.springframework.stereotype.Component;

import com.yoda.springbootmall.model.Product;


@Component
public interface ProductService {

	Product getProductById(Integer productId);
	
}
