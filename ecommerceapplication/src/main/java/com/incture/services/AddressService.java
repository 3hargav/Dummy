package com.incture.services;

import com.incture.DTO.AddressDTO;
import com.incture.response.ResponseMessage;

public interface AddressService {

	ResponseMessage getAddressByUser(int userId);

	ResponseMessage addAdress(AddressDTO addressDTO);

	ResponseMessage editAdrs(AddressDTO addressDTO, int addressId);

	ResponseMessage deleteAddress(int addressId);

}
