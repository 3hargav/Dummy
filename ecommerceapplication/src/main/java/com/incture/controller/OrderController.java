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

import com.incture.DTO.OrderDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@PostMapping("/place/{userId}/{addressId}")
	public ResponseMessage createOrder(@PathVariable("addressId") int addressId, @PathVariable("userId") int userId) {
		return orderService.addOrder(addressId, userId);	
	}
	
	@GetMapping("/myorders/{userId}")
	public ResponseMessage getAllOrders(@PathVariable("userId") int userId){
		return orderService.getMyOrders(userId);
	}
	
	@GetMapping("/admin/getall")
	public ResponseMessage getAll(){
		return orderService.getAllOrders();
	}
	
	@PutMapping(value = "admin/update/{orderId}")
	public ResponseMessage editAOrder(@RequestBody OrderDTO orderDTO, @PathVariable("orderId") int orderId){
		return orderService.editOrder(orderDTO, orderId);
	}
	
	@DeleteMapping(value = "/delete/{orderId}")
	public ResponseMessage delOrder(@PathVariable("orderId") int orderId) {
		return orderService.deleteOrder(orderId);
	} 
}
