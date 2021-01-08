package com.incture.services;

import com.incture.DO.ProductDO;
import com.incture.DTO.ProductDTO;
import com.incture.response.ResponseMessage;

public interface ProductService {

	ResponseMessage getAllProducts();

	ResponseMessage getProduct(int productId);

	ResponseMessage addProduct(ProductDTO productDTO);

	ResponseMessage deleteProduct(int productId);

	ResponseMessage editProduct(ProductDTO productDTO, int productId);

	ResponseMessage getProductByBrand(int brandId);

	ResponseMessage getProductByCategory(int categoryId);

	ProductDO getByID(int productId);

}