package com.yoda.springbootmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yoda.springbootmall.dao.impl.ProductDao;
import com.yoda.springbootmall.model.Product;
import com.yoda.springbootmall.service.ProductService;



@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

}
