package com.foodboxApp.Service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Foodbox_App.Exception.BusinessException;
import com.foodboxApp.Entity.UserEntity;
import com.foodboxApp.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	    private UserRepository userRepository;
	 
	@Override
	public UserEntity createUserDetails(UserEntity usEntity) throws BusinessException {
		Long id = usEntity.getId();
		UserEntity oldAdmin = null;
		try {
			oldAdmin = userRepository.findById(id).get();
		}catch(NoSuchElementException e) {
			
		}
		if(oldAdmin!=null) throw new BusinessException("User already exists with id: "+id);
		return userRepository.save(usEntity);
	}

	@Override
	public UserEntity resetPassword(UserEntity usEntity) {
		// TODO Auto-generated method stub
		return userRepository.save(usEntity);
	}
	

	public UserEntity getUserById(long id) throws BusinessException {
		UserEntity urEntity = null;
		try {
			if(id<=0) throw new BusinessException("user Id can not be negative or zero");
			urEntity = userRepository.findById(id);
		}catch(NoSuchElementException e) {
			throw new BusinessException("User not found with Id: "+id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urEntity;
	}
	
}
