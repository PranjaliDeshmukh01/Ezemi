package com.ezemi.resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.ProductDto;
import com.ezemi.dto.RegAdminDto;
import com.ezemi.dto.Status;
import com.ezemi.dto.Status.StatusType;
import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminService adminService ;
	
	
	@PostMapping(path="/addcategory")
	public Status addOrUpdatecategory(@RequestBody Category ct) {
		adminService.addOrUpdatecategory(ct);
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Category Added!");
		
		return status;
		
	}
	
	
	@PostMapping(path = "/addproduct")
	public Status addOrUpdateProduct(@ModelAttribute ProductDto productDto) {
		
		String imageUploadLocation = "d:/uploads/products/";
		String fileName = productDto.getProductImgUrl().getOriginalFilename();
		String targetFile = imageUploadLocation + fileName;
		
		try {
		    FileCopyUtils.copy(productDto.getProductImgUrl().getInputStream(), new FileOutputStream(targetFile));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		    Status status = new Status();
		    status.setStatus(StatusType.FAILURE);
		    status.setMessage(e.getMessage());
		    return status;
		}
		
		Product product = new Product();
	
		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());
		product.setProductDetails(productDto.getProductDetails());
		product.setProcessingFee(productDto.getProcessingFee());
		
		product.setInStock(true);
		product.setDateAdded(LocalDate.now());
		
		product.setProductImgUrl(fileName);
	
		Category category =  adminService.getCategoryById(productDto.getCategoryId());
		product.setCategory(category);
		
		adminService.addProduct(product);
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Product Added!");
		
		return status;
		
	}
	
	@PostMapping(path="/addcardtype")
	public Status addOrUpdateCardType(@RequestBody CardType cardType) {
		
		adminService.addOrUpdateCardType(cardType);
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Card Type Added!");
		
		return status;
	}
	
	@PostMapping(path="/addbank")
	public Status addOrUpdateBank(@RequestBody Bank bank) {
		adminService.addBank(bank);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Bank Added!");
		return status;
	}
	
	@GetMapping(path="/activateuser")
	public Status activateUser(@RequestParam("userId") int userId) {
		adminService.activateCustomer(userId);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("User Activated!");
		return status;
	}
	
	@PostMapping("/registeradmin")
	public Status register(@RequestBody RegAdminDto regdto) {
		return adminService.addAdmin(regdto.getName(), regdto.getEmailId(), regdto.getPassword());
	}
	
}


