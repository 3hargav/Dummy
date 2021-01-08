package com.incture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.ProductDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.ProductService;

@RestController
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/allproducts")
	public ResponseMessage getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping(value = "/fetchByBrand/{brandId}")
	public ResponseMessage getProductByBrand(@PathVariable int brandId) {
		return productService.getProductByBrand(brandId);
	}
		
	@GetMapping(value = "/fetchByCategory/{categoryId}")
	public ResponseMessage getProductByCategory(@PathVariable int categoryId) {
		return productService.getProductByCategory(categoryId);	
	}
	
	@GetMapping(value = "admin/fetchById/{productId}")
	public ResponseMessage getProductById(@PathVariable int productId) {
		return productService.getProduct(productId);
	}
	
	@PostMapping(value = "/admin/addProduct")
	public ResponseMessage addProduct(@RequestBody ProductDTO productDTO) {
		return productService.addProduct(productDTO);
	}

	@DeleteMapping(value = "/admin/delete/{productId}")
	public ResponseMessage deleteAProduct(@PathVariable("productId") int productId) {
		return productService.deleteProduct(productId);
	} 
	
	@PutMapping(value = "admin/edit/{productId}")
	public ResponseMessage editAProduct(@RequestBody ProductDTO productDTO, @PathVariable("productId") int productId){
		return productService.editProduct(productDTO, productId);	
	}

}