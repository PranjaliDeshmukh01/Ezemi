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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.ProductDto;
import com.ezemi.dto.Status;
import com.ezemi.dto.Status.StatusType;
import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.service.AdminService;
import com.ezemi.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	

	@Autowired
	ProductService productService;
	
	@Autowired
	AdminService adminService;
	
	@GetMapping(path="/allproducts")
	public List<Product> getAllProducts(){ 
		return productService.getAllProducts();
	}
	
	@GetMapping(path="/allcategory")
	public List<Category> getAllCategory(){
		return productService.getAllCategory();
	}
	

	@GetMapping("/getAll")
	public List<Product> getAllProducts2(HttpServletRequest request) {
		//fetching customer data from the database
		
		List<Product> plist = productService.getAllProducts();
	
		//reading the project's deployed folder location
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";
		//creating a folder within the project where we will place the profile pic of the customer getting fetched
		File f = new File(tempDownloadPath);
		if(!f.exists())
			f.mkdir();
		
		for(Product product : plist) {
			String targetFile = tempDownloadPath + product.getProductImgUrl();
			//the original location where the uploaded images are present
			String uploadedImagesPath = "D:/uploads/products/";
			String sourceFile = uploadedImagesPath +product.getProductImgUrl();
			
			try {
				FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
			} catch (IOException e) {
				e.printStackTrace();
				//maybe for this customer there is no profile pic
			}
		}
		return plist;
	}
	
	@GetMapping(path="/product")
	public Product getProductById(@RequestParam("productId") int productId){ 
		return productService.getProductById(productId);
	}

	
	@PostMapping(path = "/updateproductdetails")
	public Status updateProductDetails(@ModelAttribute ProductDto productDto, @RequestParam("productId") int productId) {
	
	Product product = productService.getProductById(productId);

	product.setProductName(productDto.getProductName());
	product.setPrice(productDto.getPrice());
	product.setProductDetails(productDto.getProductDetails());
	product.setProcessingFee(productDto.getProcessingFee());
	
	product.setInStock(true);
	product.setDateAdded(LocalDate.now());
	
	

	Category category =  adminService.getCategoryById(productDto.getCategoryId());
	product.setCategory(category);
	
		try {

			String imageUploadLocation = "d:/uploads/products/";
			String fileName = productDto.getProductName() + productDto.getProductImgUrl().getOriginalFilename();
			String targetFile = imageUploadLocation + fileName;
		    FileCopyUtils.copy(productDto.getProductImgUrl().getInputStream(), new FileOutputStream(targetFile));
		    product.setProductImgUrl(fileName);
		} 
		catch (IOException e) {
			adminService.addProduct(product);
		    e.printStackTrace();
		    Status status = new Status();
		    status.setStatus(StatusType.FAILURE);
		    status.setMessage(e.getMessage());
		    return status;
		}
		adminService.addProduct(product);
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Product Updated!");
		
		return status;
	}
	
	@GetMapping(path="/instocktoggle")
	public boolean inStockToggle(@RequestParam int productId){
		return productService.inStockToggle(productId);
	}
}
