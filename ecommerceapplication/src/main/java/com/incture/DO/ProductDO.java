package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRODUCT")
public class ProductDO {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "PRODUCT_ID")
	private Integer productId;
	
	@Column(name="NAME",nullable=false,length=100)
	private String name;
	
	@Column(name="CATEGORY_ID",nullable=false)
	private Integer categoryId;
	
	@Column(name="BRAND_ID",nullable=false)
	private Integer brandId;
	
	@Column(name="UNIT_PRICE",nullable=false)
	private Integer unitPrice;
	
	@Column(name="QUANTITY",nullable=false,length=10)
	private Integer quantity;
	
	@Column(name="PRODUCT_DESCRIPTION",nullable=false)
	private String productDescription;
	
	@Column(name="MANUFATURING_DATE")
	private String manufacturingDate;
	
	@Column(name="EXPIRY_DATE")
	private String expiryDate;
	
	@Column(name="URL",length = 500)
	private String url;

	@Column(name="CAPACITY")
	private String capacity;

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(String string) {
		this.manufacturingDate = string;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String string) {
		this.expiryDate = string;
	}

	
	@Override
	public String toString() {
		return "ProductDO [productId=" + productId + ", name=" + name + ", categoryId=" + categoryId + ", brandId="
				+ brandId + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", productDescription="
				+ productDescription + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate + "]";
	}
	
}