package com.foodboxApp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")

public class ProductEntity {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long productid;
	
		private String productName;
	    private int productPrice;
	    private String  productCategory;
	    private int productQuant;
	    private String  productImg;
	
	    public ProductEntity() {};
	    
		public ProductEntity(long productid, String productName, int productPrice, String productCategory, int productQuant,
				String productImg) {
			super();
			this.productid = productid;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productCategory = productCategory;
			this.productQuant = productQuant;
			this.productImg = productImg;
		}
		
    public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductQuant() {
		return productQuant;
	}
	public void setProductQuant(int productQuant) {
		this.productQuant = productQuant;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}


}
