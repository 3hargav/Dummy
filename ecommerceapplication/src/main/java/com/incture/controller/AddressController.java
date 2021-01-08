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

import com.incture.DTO.AddressDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.AddressService;

@RestController
@RequestMapping(value="/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping(value = "/fetchAddresses/{userId}")
	public ResponseMessage getaddress(@PathVariable int userId) {
		return addressService.getAddressByUser(userId);
	}
	
	@PostMapping(value = "/addAddress/{userId}")
	public ResponseMessage addAdrrss(@RequestBody AddressDTO addressDTO, @PathVariable("userId") int userId) {
		addressDTO.setUserId(userId);
		return addressService.addAdress(addressDTO);
	}
	
	@PutMapping(value = "/edit/{userId}/{addressId}")
	public ResponseMessage editAddress(@RequestBody AddressDTO addressDTO, @PathVariable("addressId") int addressId, @PathVariable("userId") int userId){
		addressDTO.setUserId(userId);
		return addressService.editAdrs(addressDTO, addressId);	
	}
	
	@DeleteMapping(value = "/delete/{addressId}")
	public ResponseMessage deleteAddres(@PathVariable("addressId") int addressId) {
		return addressService.deleteAddress(addressId);
	} 
}
