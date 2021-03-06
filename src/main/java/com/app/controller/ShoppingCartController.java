package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.Builder;
import com.app.helper.Constants;
import com.app.model.TempShoppingCart;
import com.app.pojo.BasePojo;
import com.app.pojo.PojoShoppingCart;
import com.app.service.ShoppingCartService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService cartService;
	
	@PostMapping("/add-temp")
	public ResponseEntity<?> addTempCart(@RequestBody TempShoppingCart cart) throws Exception {
		try {
			cartService.addTempCart(cart);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/get-cart")
	public ResponseEntity<?> getCartTempByUserId(@RequestParam String userId) throws Exception {
		try {			
			return new ResponseEntity<>(cartService.getCartTempByUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestBody PojoShoppingCart cart) throws Exception {
		try {
			cartService.checkout(cart);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/get-payment-method")
	public ResponseEntity<?> getAllPayment() throws Exception {
		try {			
			return new ResponseEntity<>(cartService.getAllPayment(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/get-courier")
	public ResponseEntity<?> getAllCourier() throws Exception {
		try {			
			return new ResponseEntity<>(cartService.getAllCourier(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/get-voucher")
	public ResponseEntity<?> getAllVoucher() throws Exception {
		try {			
			return new ResponseEntity<>(cartService.getAllVoucher(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete-item")
	public ResponseEntity<?> deleteItem(@RequestBody List<BasePojo> listItem) throws Exception {
		try {
			cartService.deleteItem(listItem);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
}
