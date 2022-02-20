package com.foodboxApp.Service;


import java.util.List;

import com.foodboxApp.Entity.ProductEntity;
import com.Foodbox_App.Exception.BusinessException;



public interface FoodProductService {
	
	
	public ProductEntity addProduct(ProductEntity prEntity) throws BusinessException;
	public ProductEntity getProductById(long Productid) throws BusinessException;
	public ProductEntity updateProduct(ProductEntity prEntity);
	public void deleteProductById(long Productid) throws BusinessException;
	public List<ProductEntity> getAllProduct();

}
