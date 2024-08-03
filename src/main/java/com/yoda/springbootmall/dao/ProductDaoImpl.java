package com.yoda.springbootmall.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.yoda.springbootmall.dao.impl.ProductDao;
import com.yoda.springbootmall.dto.ProductRequest;
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
	
	@Override
	public Integer createProduct(ProductRequest productRequest) {
		
		String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)"
				+ "VALUES(:productName, :category, :imageUrl , :price, :stock, :description, :createdDate, :lastModifiedDate)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		//KeyHolder存ID
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
		
		int productId = keyHolder.getKey().intValue();
		
		return productId;
	}
	
	
	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, price = :price, stock = :stock, description = :description,"  + 
					 "last_modified_date = :lastModifiedDate WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productId",productId);
		
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		
		map.put("lastModifiedDate", new Date());
		
		namedParameterJdbcTemplate.update(sql, map);
		
	}
	
	@Override
	public Integer deleteProduct(Integer productId) {
		
		String sql = "DELETE FROM product WHERE product_id = :productId";

		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		
		
		namedParameterJdbcTemplate.update(sql, map);
	
		
		return productId;
	}
}
