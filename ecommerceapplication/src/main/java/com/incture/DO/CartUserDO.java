package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CART_USER")
public class CartUserDO {
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "CART_ITEM_ID")
	private Integer cartItemId;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="PRICE")
	private Integer price;
	
	@Column(name="USER_ID")
	private Integer userId;
	
    @ManyToOne
    @JoinColumn(name="productId")
    private ProductDO productDO;
    
    @Column(name="IS_PLACED")
	private boolean isPlaced;
    
	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
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

	public ProductDO getProductDO() {
		return productDO;
	}

	public void setProductDO(ProductDO productDO) {
		this.productDO = productDO;
	}
	
   

}
