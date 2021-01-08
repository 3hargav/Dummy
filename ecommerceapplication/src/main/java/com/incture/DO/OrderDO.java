package com.incture.DO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDO {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "USER_ORDER_ID")
	private Integer userOrderId;

	@Column(name = "ADDRESS_ORDER_ID")
	private Integer addressOrderId;

	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "TOTAL_CART_VALUE")
	private Integer totalCartValue;
	
	@Column(name = "PAYMENT_TYPE")
	private String paymentMethod;
	
	@Column(name = "DATE_ORDERED")
	private Date dateOrdered;
	
	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getAddressOrderId() {
		return addressOrderId;
	}

	public void setAddressOrderId(Integer addressOrderId) {
		this.addressOrderId = addressOrderId;
	}

	public Integer getTotalCartValue() {
		return totalCartValue;
	}

	public void setTotalCartValue(Integer totalCartValue) {
		this.totalCartValue = totalCartValue;
	}

	public Integer getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(Integer userOrderId) {
		this.userOrderId = userOrderId;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
