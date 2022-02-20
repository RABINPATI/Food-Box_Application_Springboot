package com.foodboxApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodboxApp.Entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository < ProductEntity, Long > {
    ProductEntity findByProductName(String productName);
    ProductEntity findById(long productid);
}
