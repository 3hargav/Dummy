package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.AddressDAO;
import com.incture.DO.ShippingAddressDO;
import com.incture.DTO.AddressDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.AddressService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{
	@Autowired
	AddressDAO addressDAO;

	public ResponseMessage getAddressByUser(int userId) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> addressDTOs=null;
		try{
			if(!ServicesUtil.isEmpty(userId)){

			List<ShippingAddressDO> addresses = addressDAO.getAddressByUser(userId);
			if(!ServicesUtil.isEmpty(addresses)){
				addressDTOs=new ArrayList<Object>();
				for(ShippingAddressDO address:addresses){
					addressDTOs.add(addressDAO.ExportFromDB(address));
				}
			}
			}responseMessage.setStatusMessage("All addresses for User fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(addressDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All addresses fetching for user failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(addressDTOs);
			return responseMessage;
		}
	}

	public ResponseMessage addAdress(AddressDTO addressDTO) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			
			if(!ServicesUtil.isEmpty(addressDTO)){
				addressDAO.addAdrs(addressDTO);
				responseMessage.setStatusMessage("Successfully added address");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in AddressServiceImpl.addaadress():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in AddressServiceImpl.addaadress():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage editAdrs(AddressDTO addressDTO, int addressId) {
	
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(!ServicesUtil.isEmpty(addressDTO)){
		ShippingAddressDO addressOld = addressDAO.getAddressById(addressId);
		
		addressOld.setAddress(addressDTO.getAddress());
		addressOld.setCity(addressDTO.getCity());
		addressOld.setState(addressDTO.getState());
		addressOld.setCountry(addressDTO.getCountry());
		addressOld.setPincode(addressDTO.getPincode());
		addressOld.setUserId(addressDTO.getUserId());

		addressDAO.saveToDB(addressOld);
	   }

		responseMessage.setStatusMessage("Successfully edited address");
		responseMessage.setStatusCode(200);
		return responseMessage;	
     }
		catch(Exception e){
			System.err.println("Exception in AddressServiceImpl.editAddress():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in AddressServiceImpl.editAddress():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage deleteAddress(int addressId) {
		ResponseMessage responseMessage=new ResponseMessage();	
		try{
			
			if(!ServicesUtil.isEmpty(addressId)){
				addressDAO.deleteAddress(addressId);
				responseMessage.setStatusMessage("Successfully deleted address");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in AddressServiceImpl.deleteaddress():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in AddressServiceImpl.deleteaddress():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

}
