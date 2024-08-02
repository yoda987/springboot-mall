package com.yoda.springbootmall.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.yoda.springbootmall.dao.impl.ProductDao;
import com.yoda.springbootmall.model.Product;
import com.yoda.springbootmall.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Product getProductById(Integer productId) {
		
		String sql = "Select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date from product where product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		
		List<Product> productsList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		
		if(productsList.size() > 0) {
			return productsList.get(0);
		}else {
			return null;
		}
		
	}
}
