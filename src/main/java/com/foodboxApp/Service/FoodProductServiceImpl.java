package com.foodboxApp.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Foodbox_App.Exception.BusinessException;
import com.foodboxApp.Entity.ProductEntity;
import com.foodboxApp.Repository.ProductRepository;

@Service
public class FoodProductServiceImpl implements FoodProductService {

	@Autowired
	ProductRepository prRepo;
	
	@Override
	public ProductEntity addProduct(ProductEntity prEntity) throws BusinessException {
		long productId = prEntity.getProductid();
		ProductEntity oldPr = null;
		try {
			oldPr = prRepo.findById(productId);
		}catch(NoSuchElementException e) {
			
		}
		if(oldPr!=null) throw new BusinessException("Product already exists with id: "+productId);
		return prRepo.save(prEntity);	
	}

	
	@Override
	public ProductEntity updateProduct(ProductEntity prEntity) {
		return prRepo.save(prEntity);
	}

	@Override
	public void deleteProductById(long Productid) throws BusinessException {
		try {
			prRepo.deleteById(Productid);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("Invalid id: "+Productid);
		}catch(EmptyResultDataAccessException e) {
			throw new BusinessException("Product does not exist with Id: "+Productid);
		}
		
	}

	@Override
	public List<ProductEntity> getAllProduct() {
		return prRepo.findAll();
	}
	
	@Override
	public ProductEntity getProductById(long productid) throws BusinessException {
		ProductEntity pr = null;
		try {
			if(productid<=0) throw new BusinessException("Product Id can not be negative or zero");
			pr = prRepo.findById(productid);
		}catch(NoSuchElementException e) {
			throw new BusinessException("Product not found with Id: "+productid);
		}
		return pr;
	}

}
