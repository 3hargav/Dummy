package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.AddressDAO;
import com.incture.DAO.CartUserDAO;
import com.incture.DAO.OrderDAO;
import com.incture.DO.CartOrder;
import com.incture.DO.CartUserDO;
import com.incture.DO.OrderDO;
import com.incture.DTO.CartUserDTO;
import com.incture.DTO.OrderDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.OrderService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	CartUserDAO cartUserDAO;
	
	@Autowired
	AddressDAO addressDAO;

	public ResponseMessage addOrder(int addressId, int userId) {
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			
			if(!ServicesUtil.isEmpty(addressId) && !ServicesUtil.isEmpty(userId)){
				
				OrderDO order = new OrderDO();

				List<CartUserDO> cartitems = cartUserDAO.getAllData(userId);
				
				String status = "placed";
						
				order.setAddressOrderId(addressId);;
				order.setUserOrderId(userId);
				order.setTotalCartValue(ServicesUtil.getCartTotalPrice(cartitems));
				order.setOrderStatus(status);
				order.setPaymentMethod("Cash On Delivery");
				
				Date date = new Date();
				order.setDateOrdered(date);
				
				orderDAO.addOrderDetails(order);
				
				for(CartUserDO item : cartitems){
					orderDAO.saveToJoinTable(item.getCartItemId(), order.getOrderId());
					item.setPlaced(Boolean.TRUE);
					cartUserDAO.saveToDB(item);
				}
				
				responseMessage.setStatusMessage("Successfully added Order");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in OrderServiceImpl.addOrder():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in OrderServiceImpl.addOrder():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage getMyOrders(int userId) {

		ResponseMessage responseMessage=new ResponseMessage();
		
		List<Object> myorders = null;
		try{
			List<OrderDO> ordersDO = orderDAO.getAllOrderes(userId);
			if(!ServicesUtil.isEmpty(ordersDO)){
				myorders = new ArrayList<Object>();
				for(OrderDO item:ordersDO){
					OrderDTO orderItem = orderDAO.ExportFromDB(item);
					orderItem.setAddress(addressDAO.ExportFromDB(addressDAO.getAddressById(orderItem.getAddressOrderId())));
					
					List<CartOrder> cartOrders = orderDAO.getCartOrderData(orderItem.getOrderId());
					List<CartUserDTO> cartItemDTOs = new ArrayList<CartUserDTO>();
					
					if(!ServicesUtil.isEmpty(cartOrders)){
						for(CartOrder itemcartOrder:cartOrders){
							CartUserDO  itemNew = cartUserDAO.getCartitemById(itemcartOrder.getCartItemId());
							cartItemDTOs.add((CartUserDTO) cartUserDAO.ExportFromDB(itemNew));
						}
					}
					
				orderItem.setCartitemList(cartItemDTOs);
				myorders.add(orderItem);
				}
			}responseMessage.setStatusMessage("fetching myOrders success");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(myorders);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("fetching user orders failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(myorders);
			return responseMessage;
		}

	}

	public ResponseMessage getAllOrders() {
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> ordersDtos=null;
		try{
			List<OrderDO> orders = orderDAO.getAllData();
			if(!ServicesUtil.isEmpty(orders)){
				ordersDtos=new ArrayList<Object>();
				for(OrderDO order:orders){
					ordersDtos.add(orderDAO.ExportFromDB(order));
				}
			}responseMessage.setStatusMessage("All orders fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(ordersDtos);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All orders fetching failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(ordersDtos);
			return responseMessage;
		}

	}

	public ResponseMessage editOrder(OrderDTO orderDTO, int orderId) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(!ServicesUtil.isEmpty(orderDTO)){
			OrderDO orderoOld = orderDAO.getOrderId(orderId);		
			orderoOld.setOrderStatus(orderDTO.getOrderStatus());
			orderDAO.saveToDB(orderoOld);
			}
		responseMessage.setStatusMessage("Successfully updated order");
		responseMessage.setStatusCode(200);
		return responseMessage;	
		}
		catch(Exception e){
			System.err.println("Exception in OrderServiceImpl.editOrder():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in OrderServiceImpl.editOrder():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage deleteOrder(int orderId) {
		
		ResponseMessage responseMessage=new ResponseMessage();	
		try{
			
			if(!ServicesUtil.isEmpty(orderId)){
				orderDAO.delOrder(orderId);
				responseMessage.setStatusMessage("Successfully deleted ordered");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in OrderServiceImpl.deleteOrder():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in OrderServiceImpl.deleteOrder():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}
}
