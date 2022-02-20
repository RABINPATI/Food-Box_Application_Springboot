package com.foodboxApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodboxApp.Entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository < UserEntity, Long > {
    UserEntity findByEmail(String email);
    UserEntity findById(long id);
}

