package com.incture.services;

import com.incture.DTO.OrderDTO;
import com.incture.response.ResponseMessage;

public interface OrderService {


	ResponseMessage addOrder(int addressId, int userId);

	ResponseMessage getMyOrders(int userId);

	ResponseMessage getAllOrders();

	ResponseMessage editOrder(OrderDTO orderDTO, int orderId);

	ResponseMessage deleteOrder(int orderId);


}
