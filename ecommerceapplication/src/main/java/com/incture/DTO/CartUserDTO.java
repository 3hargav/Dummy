package com.incture.DTO;

import com.incture.DO.ProductDO;

public class CartUserDTO {
	
	private Integer cartItemId;
	private ProductDO product;
	private Integer quantity;
	private Integer price;
	private Integer userId;
	
	public Integer getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}
	public ProductDO getProduct() {
		return product;
	}
	public void setProduct(ProductDO product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
