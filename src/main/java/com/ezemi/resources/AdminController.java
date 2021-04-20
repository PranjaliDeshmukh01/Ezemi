package com.ezemi.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ezemi.entity.User;
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
	
	@GetMapping(path="/allbank")
	public List<Bank> getAllBank(){
		return adminService.getallBanks();
	}
	
	
	@GetMapping(path="/allcardtype")
	public List<CardType> getAllCardTypes(){
		return adminService.getAllCardType();
	}
	
	
//	@PostMapping(path="/deleteacategory")
//	public Status deleteACategory(@RequestParam("categoryId") int categoryId) {
//		
//		adminService.deleteACategory(categoryId);
//		Status status = new Status();
//		status.setStatus(StatusType.SUCCESS);
//		status.setMessage("Deleted Category!");
//		return status;
//	}
	
	@GetMapping(path="/getnotapprovedusers")
	public List<User> getNotApprovedUsers(HttpServletRequest request){
		List<User> userList= adminService.getNotApprovedUsers();

		
		//reading the project's deployed folder location
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		//creating a folder within the project where we will place the profile pic of the customer getting fetched
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		
		for(User user : userList) {
			String adhartargetFile = tempDownloadPath + user.getAdharCard();
			String pantargetFile = tempDownloadPath + user.getPanCard();
			
			//the original location where the uploaded images are present
			String uploadedImagesPath = "D:/uploads/products/";
			String adharsourceFile = uploadedImagesPath +user.getAdharCard();
			String pansourceFile = uploadedImagesPath +user.getPanCard();
			
			
			try {
				FileCopyUtils.copy(new File(adharsourceFile), new File(adhartargetFile));
				FileCopyUtils.copy(new File(pansourceFile), new File(pantargetFile));
				
			} catch (IOException e) {
				e.printStackTrace();
				//maybe for this customer there is no profile pic
			}
		}

		return userList;
	}
	
	@GetMapping(path="/getapprovedusers")
	public List<User> getApprovedUsers(){
		return adminService.getApprovedCustomers();
	}
	
	
}


