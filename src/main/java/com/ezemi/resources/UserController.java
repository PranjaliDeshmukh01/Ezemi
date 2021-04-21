package com.ezemi.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.OrderDto;
import com.ezemi.dto.Status;
import com.ezemi.dto.Status.StatusType;
import com.ezemi.entity.Address;
import com.ezemi.entity.Order;
import com.ezemi.entity.User;
import com.ezemi.service.OrderService;
import com.ezemi.service.UserService;



@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;



	@GetMapping("/activatecard")
	public Status activateCard(@RequestParam("userId") int userId) {
		userService.activateCard(userId);
		Status status=new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Transaction Succesful !");
		return status;
	}
	
	@GetMapping("/getuserbyid")
	public User getUserById(@RequestParam("userId") int userId) {
		User user=userService.getUserById(userId);
		return user;
	}
		
	@GetMapping(value="/getAddressByUserId")
	public Address getAddressByUserId(@RequestParam("userId") int userId) {
		return getUserById(userId).getAddress();
	}
	
	@PutMapping(value="/updateAddress")
	public Status updateAddress(@RequestParam("userId") int userId, @RequestBody Address address) {
		userService.updateAddress(userId, address);
		Status status=new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Address Updated !");
		return status;
	}
	
	
	@PostMapping(value="/confirmorder")
	public Status confirmOrder(@RequestBody OrderDto orderdto) {
		orderService.makeAnOrder(orderdto.getUserId(), orderdto.getEmimonths(), orderdto.getProductId(), orderdto.isAutoDebit());
		Status status=new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Order placed!");
		return status;
	}
	
	@GetMapping(value="/getOrdersByUserId")
	public List<Order> getOrdersByUserId(@RequestParam("userId") int userId){
		return orderService.getOrdersByUserId(userId);
	}
	
	@GetMapping(value="/getUnPaidOrdersByUserId")
	public List<Order> getUnPaidOrdersByUserId(@RequestParam("userId") int userId){
		return orderService.getNotFullyPaidOrders(userId);
	}
	
	@GetMapping(value="/getPaidOrdersByUserId")
	public List<Order> getPaidOrdersByUserId(@RequestParam("userId") int userId){
		return orderService.getFullyPaidOrders(userId);
	}
}
