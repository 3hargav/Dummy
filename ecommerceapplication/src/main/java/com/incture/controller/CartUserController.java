package com.incture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DO.ProductDO;
import com.incture.DTO.CartUserDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.CartUserService;
import com.incture.services.ProductService;

@RestController
@RequestMapping(value="/usercart")
public class CartUserController {

	@Autowired
	CartUserService cartUserService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/additem/{userId}/{productId}/{quantity}")
	public ResponseMessage addProduct(@PathVariable("productId") int productId, @PathVariable("userId") int userId, @PathVariable("quantity") int quantity) {
		ProductDO product = productService.getByID(productId);
		CartUserDTO cartuser = new CartUserDTO();
		cartuser.setUserId(userId);
		cartuser.setProduct(product);
		cartuser.setPrice(product.getUnitPrice() * quantity);
		cartuser.setQuantity(quantity);
		
		return cartUserService.additem(cartuser);
	}
	
	@GetMapping("/getcart/{userId}")
	public ResponseMessage getCartdata(@PathVariable("userId") int userId){
		return cartUserService.getcart(userId);
	}	
	
	@DeleteMapping(value = "/delete/{cartItemId}")
	public ResponseMessage removeitem(@PathVariable("cartItemId") int cartItemId) {
		return cartUserService.deletecartItem(cartItemId);
	}
}
