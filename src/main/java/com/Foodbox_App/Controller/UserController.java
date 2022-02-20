package com.Foodbox_App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Foodbox_App.Exception.BusinessException;
import com.foodboxApp.Entity.ProductEntity;
import com.foodboxApp.Entity.UserEntity;
import com.foodboxApp.Repository.UserRepository;
import com.foodboxApp.Service.FoodProductService;
import com.foodboxApp.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	 @Autowired
	    private UserRepository userRepository;

	 
	  @Autowired
			private UserService implService;
	  
	  @Autowired
		private FoodProductService foodImplService;
		  
		  private MultiValueMap<String, String> errorMap;
	 
	 @GetMapping("/role")
		public String getuser(){
			return "HI rabin";
		}
	 @PostMapping(path = "/addUser")
	    public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,@RequestParam String password) {

	        UserEntity user = new UserEntity();
	        user.setFirstName(firstName);
	        user.setLastName(lastName);
	        user.setEmail(email);
	        user.setPassword(password);
	        userRepository.save(user);
	        return "User Created";
	    }
		 
		 @PostMapping("/role")
			public ResponseEntity<UserEntity> createUserDetails(@RequestBody UserEntity urEntity) {
				try {
					return new ResponseEntity<>(implService.createUserDetails(urEntity), HttpStatus.OK);
				} catch (BusinessException e) {
					errorMap = new LinkedMultiValueMap<>();
					errorMap.add("errorMessage:", e.getMessage());
					return new ResponseEntity<>(null, errorMap, HttpStatus.BAD_REQUEST);
				}
			}

			/**
			 * AdminUser get request controller
			 * 
			 * @param id
			 * @return ResponseEntity<Shoes> with the given id
			 */
			@GetMapping("/role/{id}")
			public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
				try {
					return new ResponseEntity<>(implService.getUserById(id), HttpStatus.OK);
				} catch (BusinessException e) {
					errorMap = new LinkedMultiValueMap<>();
					errorMap.add("errorMessage:", e.getMessage());
					return new ResponseEntity<>(null, errorMap, HttpStatus.NOT_FOUND);
				}
			}
			
			@PutMapping("/resetPassword")
			public ResponseEntity<UserEntity> resetPassword(@RequestBody UserEntity urEntity) {
				return new ResponseEntity<>(implService.resetPassword(urEntity), HttpStatus.OK);
			}
			
			@GetMapping(path = "/allProduct")
			public ResponseEntity<List<ProductEntity>> getAllProduct(){
				return new ResponseEntity<List<ProductEntity>>(foodImplService.getAllProduct(), HttpStatus.OK);
		    }
		 
			

	}