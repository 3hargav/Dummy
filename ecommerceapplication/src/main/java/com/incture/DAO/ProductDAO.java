package com.incture.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.ProductDO;
import com.incture.DTO.ProductDTO;

@Repository
public class ProductDAO extends BaseDao{

	@SuppressWarnings("unchecked")
	public List<ProductDO> getAllData() {
		Session session= getSession();
		List<ProductDO> productList=session.createQuery("from ProductDO").list();
		return productList;	
	}

	private ProductDO importToDB(ProductDTO productDTO) {
		ProductDO productDO = new ProductDO();
		productDO.setBrandId(productDTO.getBrandId());
		productDO.setCategoryId(productDTO.getCategoryId());
		productDO.setName(productDTO.getName());
		productDO.setManufacturingDate(productDTO.getManufacturingDate());
		productDO.setExpiryDate(productDTO.getExpiryDate());
		productDO.setProductDescription(productDTO.getProductDescription());
		productDO.setUnitPrice(productDTO.getUnitPrice());
		productDO.setQuantity(productDTO.getQuantity());
		productDO.setCapacity(productDTO.getCapacity());
		productDO.setUrl(productDTO.getUrl());
		
		return productDO;
	}
	
	public ProductDTO ExportFromDB(ProductDO product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setBrandId(product.getBrandId());
		productDTO.setCategoryId(product.getCategoryId());
		productDTO.setName(product.getName());
		productDTO.setProductDescription(product.getProductDescription());
		productDTO.setManufacturingDate(product.getManufacturingDate());
		productDTO.setExpiryDate(product.getExpiryDate());
		productDTO.setUnitPrice(product.getUnitPrice());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setCapacity(product.getCapacity());
		productDTO.setUrl(product.getUrl());
		
		return productDTO;
	}

	public void addProductDetails(ProductDTO productDTO) {
		ProductDO productDO = importToDB(productDTO);
		Session session= getSession();
		session.save(productDO);
	}

	@SuppressWarnings("unchecked")
	public ProductDO getProductById(int productId) {
		Session session= getSession();
		Query<ProductDO> query = session.createQuery("select r from ProductDO r where r.productId='"+productId+"'");
		ProductDO productY = query.uniqueResult();
		return productY;
	}

	public void deleteById(int productId) {
		Session session= getSession();
		session.createQuery("delete from ProductDO r where r.productId="+productId+"").executeUpdate();
		session.flush();
	}
	
	public void saveToDB(ProductDO productDO) {
		Session session= getSession();
		session.save(productDO);
	}

	@SuppressWarnings("unchecked")
	public List<ProductDO> getBrandData(int brandId) {
		Session session= getSession();
		List<ProductDO> productList=session.createQuery("select r from ProductDO r where r.brandId='"+brandId+"'").list();
		return productList;	
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductDO> getCategoryData(int categoryId) {
		Session session= getSession();
		List<ProductDO> productList=session.createQuery("select r from ProductDO r where r.categoryId='"+categoryId+"'").list();
		return productList;	
	}


}