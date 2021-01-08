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
@Table(name = "GUEST_CART")
public class GuestCartDO {

	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "GUEST_CART_ID")
	private Integer guestCartId;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="PRICE")
	private Integer price;
	
    @ManyToOne
    @JoinColumn(name="productId")
    private ProductDO productDO;

	public Integer getGuestCartId() {
		return guestCartId;
	}

	public void setGuestCartId(Integer guestCartId) {
		this.guestCartId = guestCartId;
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
