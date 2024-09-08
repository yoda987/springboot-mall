package com.yoda.springbootmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.springbootmall.model.Product;
import com.yoda.springbootmall.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

		Product product = productRepository.findById(productId).orElse(null);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = (List<Product>) productRepository.findAll();
		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody Product product) {

		productRepository.save(product);
		System.out.println("新增成功");
		return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
	}

	@PutMapping("/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {

		if (productRepository.existsById(productId)) {
			product.setProductId(productId);
			productRepository.save(product);
			System.out.println("Update成功");
			return ResponseEntity.ok("Update成功");
		} else {
			System.out.println("Update失敗");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("商品 Id 錯誤");
		}
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {

		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			System.out.println("刪除成功");
			return ResponseEntity.ok("刪除成功");
		} else {
			System.out.println("刪除失敗");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("商品 Id 錯誤");
		}
	}
}
