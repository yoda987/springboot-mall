package com.yoda.springbootmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.stereotype.Component;

import com.yoda.springbootmall.dao.impl.ProductDao;
import com.yoda.springbootmall.dto.ProductRequest;
import com.yoda.springbootmall.model.Product;
import com.yoda.springbootmall.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		return productDao.createProduct(productRequest);
	}

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		productDao.updateProduct(productId, productRequest);

	}

	@Override
	public Integer deleteProduct(Integer productId) {

		Product prod = productDao.getProductById(productId);

		
		if (prod != null) {
			return productDao.deleteProduct(productId);
		}else {
			return 0;
		}

		
	}
}
