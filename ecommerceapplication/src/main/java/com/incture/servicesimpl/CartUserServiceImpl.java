package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.CartUserDAO;
import com.incture.DO.CartUserDO;
import com.incture.DTO.CartUserDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.CartUserService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class CartUserServiceImpl implements CartUserService {
	
	@Autowired
	CartUserDAO cartUserDAO;

	public ResponseMessage additem(CartUserDTO cartuser) {
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			
			if(!ServicesUtil.isEmpty(cartuser)){
				cartUserDAO.additems(cartuser);
				responseMessage.setStatusMessage("Successfully added item to the cart");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in CartUserServiceImpl.additem():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in CartUserServiceImpl.additem():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage deletecartItem(int cartItemId) {
		
		ResponseMessage responseMessage=new ResponseMessage();	
		try{
			
			if(!ServicesUtil.isEmpty(cartItemId)){
				cartUserDAO.deleteById(cartItemId);
				responseMessage.setStatusMessage("Successfully removed item from the cart");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in CartUserServiceImpl.deletecartItem():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in CartUserServiceImpl.deletecartItem():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage getcart(int userId) {
		ResponseMessage responseMessage=new ResponseMessage();
		
		List<Object> cartItemDTOs = null;
		try{
			List<CartUserDO> cartitems = cartUserDAO.getAllData(userId);
			if(!ServicesUtil.isEmpty(cartitems)){
				cartItemDTOs = new ArrayList<Object>();
				for(CartUserDO item:cartitems){
					cartItemDTOs.add(cartUserDAO.ExportFromDB(item));
				}
			}responseMessage.setStatusMessage("fetching cart success");
			responseMessage.setStatusCode(200);
			responseMessage.setTotalCartPrice(ServicesUtil.getCartTotalPrice(cartitems));
			responseMessage.setObjectList(cartItemDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("fetching cart failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(cartItemDTOs);
			return responseMessage;
		}

	}

}
