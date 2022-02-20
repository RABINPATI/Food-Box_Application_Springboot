package com.foodboxApp.Service;

import com.Foodbox_App.Exception.BusinessException;
import com.foodboxApp.Entity.UserEntity;

public interface AdminService {
	
	public UserEntity createUserDetails(UserEntity usEntity) throws BusinessException;
	public UserEntity getUserById(long id) throws BusinessException;
	public UserEntity resetPassword(UserEntity usEntity);

}
