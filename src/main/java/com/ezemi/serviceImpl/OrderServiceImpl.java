package com.ezemi.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.EmiCard;
import com.ezemi.entity.Order;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;
import com.ezemi.repository.OrderRepository;
import com.ezemi.repository.ProductRepository;
import com.ezemi.repository.TransactionRepository;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.EmailService;
import com.ezemi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ProductRepository productRepo;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	EmailService emailSerive;
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@Override
	public void makeAnOrder(int userId, int emimonths, int productId, boolean autodebit) {
		User user = userRepo.getUserById(userId);
		Product prod = productRepo.getProductById(productId);
		double totalCost = prod.getPrice() + prod.getProcessingFee();
		
		Order order = new Order();
		order.setProduct(prod);
		order.setUser(user);
		order.setOrderCost(totalCost);
		
		order.setDate(LocalDate.now());
		
		if(emimonths ==0) {
			order.setAmountDue(0);
			order.setEmiMonths(0);
			order.setInstallments(0);
		}
		else {
			order.setAutoDebit(autodebit);
			order.setEmiMonths(emimonths);
			order.setInstallments(1);
			order.setAmountDue(totalCost - (totalCost/emimonths));
			order.setEmiAmount(totalCost/emimonths);
			
			transactionRepo.addTransaction(userId, (totalCost/emimonths), "First amount of new order");
			transactionRepo.reduceCreditBy(userId, totalCost-(totalCost/emimonths));	
		}
		String text = user.getFirstname()+", your order for "+prod.getProductName()+" is Confiremed!  Thank you.";
		String subject = "Order confirmation";
		emailSerive.sendEmail(user.getEmail(), text, subject);
		orderRepo.addAnOrder(order);
	}

	
	
	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return orderRepo.getOrdersByUserId(userId);
	}
	
	

	@Override
	public List<Order> getNotFullyPaidOrders(int userId) {
		return orderRepo.getOrdersByUserId(userId).stream().filter(x -> x.getAmountDue() > 0)
				.collect(Collectors.toList());
	}
	
	

	@Override
	public List<Order> getFullyPaidOrders(int userId) {
		return orderRepo.getOrdersByUserId(userId).stream().filter(x -> x.getAmountDue() == 0)
				.collect(Collectors.toList());
	}

}
