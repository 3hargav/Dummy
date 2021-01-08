package com.incture.response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.incture.DTO.OrderDTO;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseMessage {
	private Object object;
	private List<Object> objectList;
	private List<OrderDTO> orderDTO;

	
	//private List<RegistrationDTO> registrationlist;
	private String statusMessage;
	private int statusCode;
	private int totalCartPrice;
	//ProductDTO productDTO = new ProductDTO();
	//private List<ProductDTO> productList;
	public List<OrderDTO> getOrderDTO() {
		return orderDTO;
	}
	public void setOrderDTO(List<OrderDTO> orderDTO) {
		this.orderDTO = orderDTO;
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public List<Object> getObjectList() {
		return objectList;
	}
	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getTotalCartPrice() {
		return totalCartPrice;
	}
	public void setTotalCartPrice(int totalCartPrice) {
		this.totalCartPrice = totalCartPrice;
	}
	
	
}
