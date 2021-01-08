package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CART_ORDER")	
public class CartOrder {
		
		@Id
		@GenericGenerator(name = "inc", strategy = "increment")
		@GeneratedValue(generator = "inc")
		@Column(name = "COMMON_ID")
		private Integer commonId;
		
		@Column(name = "ORDER_IDc")
		private Integer OrderId;
		
		@Column(name = "CART_ITEM_IDc")
		private Integer cartItemId;
		
		public Integer getCommonId() {
			return commonId;
		}

		public void setCommonId(Integer commonId) {
			this.commonId = commonId;
		}

		public Integer getOrderId() {
			return OrderId;
		}

		public void setOrderId(Integer orderId) {
			OrderId = orderId;
		}

		public Integer getCartItemId() {
			return cartItemId;
		}

		public void setCartItemId(Integer cartItemId) {
			this.cartItemId = cartItemId;
		}

}
