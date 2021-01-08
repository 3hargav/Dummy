package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.ProductDAO;
import com.incture.DO.ProductDO;
import com.incture.DTO.ProductDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.ProductService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	public ResponseMessage getAllProducts() {
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> productDTOs=null;
		try{
			List<ProductDO> products = productDAO.getAllData();
			if(!ServicesUtil.isEmpty(products)){
				productDTOs=new ArrayList<Object>();
				for(ProductDO product:products){
					productDTOs.add(productDAO.ExportFromDB(product));
				}
			}responseMessage.setStatusMessage("All products fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(productDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All products fetching failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(productDTOs);
			return responseMessage;
		}

	}
	
	public ResponseMessage getProduct(int productId) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		ProductDTO productDTO1 = new ProductDTO();
		try{
			ProductDO product1 = productDAO.getProductById(productId);
			if(!ServicesUtil.isEmpty(product1)){
				productDTO1=productDAO.ExportFromDB(product1);
			}
			responseMessage.setStatusMessage("Required product fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObject(productDTO1);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All products fetching failed");
			responseMessage.setStatusCode(500);
			return responseMessage;
		}

	}

	
	public ResponseMessage addProduct(ProductDTO productDTO) {

		ResponseMessage responseMessage=new ResponseMessage();
		try{
			
			if(!ServicesUtil.isEmpty(productDTO)){
				productDAO.addProductDetails(productDTO);
				responseMessage.setStatusMessage("Successfully added product");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in ProductServiceImpl.addProduct():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in ProductServiceImpl.addProduct():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}	

	
	public ResponseMessage deleteProduct(int productId) {
		ResponseMessage responseMessage=new ResponseMessage();	
		try{
			
			if(!ServicesUtil.isEmpty(productId)){
				productDAO.deleteById(productId);
				responseMessage.setStatusMessage("Successfully deleted product");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in ProductServiceImpl.deleteProduct():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in ProductServiceImpl.deleteProduct():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}
	public ResponseMessage editProduct(ProductDTO productDTO, int productId) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(!ServicesUtil.isEmpty(productDTO)){
       // int productId = productDTO.getProductId();
		ProductDO productDoOld = productDAO.getProductById(productId);
		
		productDoOld.setBrandId(productDTO.getBrandId());
		productDoOld.setCategoryId(productDTO.getCategoryId());
		productDoOld.setName(productDTO.getName());
		productDoOld.setManufacturingDate(productDTO.getManufacturingDate());
		productDoOld.setExpiryDate(productDTO.getExpiryDate());
		productDoOld.setProductDescription(productDTO.getProductDescription());
		productDoOld.setUnitPrice(productDTO.getUnitPrice());
		productDoOld.setQuantity(productDTO.getQuantity());
		productDoOld.setCapacity(productDTO.getCapacity());
		productDoOld.setUrl(productDTO.getUrl());

		productDAO.saveToDB(productDoOld);
	   }

		responseMessage.setStatusMessage("Successfully edited product");
		responseMessage.setStatusCode(200);
		return responseMessage;	
     }
		catch(Exception e){
			System.err.println("Exception in ProductServiceImpl.editProduct():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in ProductServiceImpl.editProduct():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage getProductByBrand(int brandId) {
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> productBrandDTOs=null;
		try{
			if(!ServicesUtil.isEmpty(brandId)){

			List<ProductDO> products = productDAO.getBrandData(brandId);
			if(!ServicesUtil.isEmpty(products)){
				productBrandDTOs=new ArrayList<Object>();
				for(ProductDO product:products){
					productBrandDTOs.add(productDAO.ExportFromDB(product));
				}
			}
			}responseMessage.setStatusMessage("All products by Brand fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(productBrandDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All products fetching by brand failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(productBrandDTOs);
			return responseMessage;
		}

	}

	public ResponseMessage getProductByCategory(int categoryId) {
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> productCategoryDTOs=null;
		try{
			if(!ServicesUtil.isEmpty(categoryId)){

			List<ProductDO> products = productDAO.getCategoryData(categoryId);
			if(!ServicesUtil.isEmpty(products)){
				productCategoryDTOs=new ArrayList<Object>();
				for(ProductDO product:products){
					productCategoryDTOs.add(productDAO.ExportFromDB(product));
				}
			}
			}responseMessage.setStatusMessage("All products by Category fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(productCategoryDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All products fetching by category failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(productCategoryDTOs);
			return responseMessage;
		}

	}

	@Override
	public ProductDO getByID(int productId) {
			ProductDO product1 = productDAO.getProductById(productId);
			
			return product1;
	}
		
}