package com.incture.services;

import com.incture.DTO.CartUserDTO;
import com.incture.response.ResponseMessage;

public interface CartUserService {

	ResponseMessage additem(CartUserDTO cartuser);

	ResponseMessage deletecartItem(int cartItemId);

	ResponseMessage getcart(int userId);

}
