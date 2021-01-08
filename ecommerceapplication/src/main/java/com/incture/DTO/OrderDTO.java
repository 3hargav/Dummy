package com.incture.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {

	private Integer orderId;
	private Integer userOrderId;
	private Integer addressOrderId;
	private String paymentMethod;
	private String orderStatus;
	private Integer totalCartValue;
	private List<CartUserDTO> cartitemList;
	private Object address;
	private Date dateOrdered;
	
	public Date getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(Date date) {
		this.dateOrdered = date;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public Object getAddress() {
		return address;
	}
	public void setAddress(Object object) {
		this.address = object;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(Integer userOrderId) {
		this.userOrderId = userOrderId;
	}
	public Integer getAddressOrderId() {
		return addressOrderId;
	}
	public void setAddressOrderId(Integer addressOrderId) {
		this.addressOrderId = addressOrderId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getTotalCartValue() {
		return totalCartValue;
	}
	public void setTotalCartValue(Integer totalCartValue) {
		this.totalCartValue = totalCartValue;
	}
	public List<CartUserDTO> getCartitemList() {
		return cartitemList;
	}
	public void setCartitemList(List<CartUserDTO> cartitemList) {
		this.cartitemList = cartitemList;
	}

		

}
