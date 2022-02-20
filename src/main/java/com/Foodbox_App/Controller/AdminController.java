package com.Foodbox_App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.foodboxApp.Repository.ProductRepository;
import com.foodboxApp.Repository.UserRepository;
import com.foodboxApp.Service.AdminService;
import com.foodboxApp.Service.FoodProductService;




@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	  @Autowired
	    private UserRepository userRepository;
	  
	  @Autowired
	    private ProductRepository prodRepository;
	  
	  @Autowired
		private AdminService implService;
	  
	  @Autowired
		private FoodProductService foodImplService;
	  
	  private MultiValueMap<String, String> errorMap;
	  
	@GetMapping("/role")
	public String getuser(){
		return "HI AdminBase";
	}
	
	@PostMapping(path = "/addAdmin")
    public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {

        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
        return "User Created";
    }
	  
	 
	@PostMapping(path = "/addProduct")
    public @ResponseBody String addNewProduct(@RequestParam String productName,
    		@RequestParam int productPrice, @RequestParam String productCategory,
    		@RequestParam int productQuant,@RequestParam String productImg) {

        ProductEntity prod = new ProductEntity();
        prod.setProductName(productName);
        prod.setProductPrice(productPrice);
        prod.setProductCategory(productCategory);
        prod.setProductQuant(productQuant);
        prod.setProductImg(productImg);
        prodRepository.save(prod);
        return "Product Added";
    }
   
	
	 
	 
		@PostMapping("/addAdminUser")
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
		
		@GetMapping("/addAdminUser/{id}")
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
		
	 
		@PostMapping("/addAdminProduct")
		public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity prEntity) {
			try {
				return new ResponseEntity<>(foodImplService.addProduct(prEntity), HttpStatus.OK);
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
		 * @return ResponseEntity<Product> with the given id
		 */
		@GetMapping("/product/{id}")
		public ResponseEntity<ProductEntity> getProductById(@PathVariable long id) {
			try {
				return new ResponseEntity<>(foodImplService.getProductById(id), HttpStatus.OK);
			} catch (BusinessException e) {
				errorMap = new LinkedMultiValueMap<>();
				errorMap.add("errorMessage:", e.getMessage());
				return new ResponseEntity<>(null, errorMap, HttpStatus.NOT_FOUND);
			}
		}
		
		@GetMapping(path = "/allProduct")
		public ResponseEntity<List<ProductEntity>> getAllProduct(){
			return new ResponseEntity<List<ProductEntity>>(foodImplService.getAllProduct(), HttpStatus.OK);
	    }
		
		@DeleteMapping("/deleteProduct/{id}")
		public ResponseEntity<String> deleteProductById(@PathVariable long id) {
			try {
				foodImplService.deleteProductById(id);
				return new ResponseEntity<>("Succesfully deleted Product with id: " + id, HttpStatus.OK);
			} catch (BusinessException e) {
				errorMap = new LinkedMultiValueMap<>();
				errorMap.add("errorMessage:", e.getMessage());
				return new ResponseEntity<>(e.getMessage(), errorMap, HttpStatus.BAD_REQUEST);
			}
		}


}
