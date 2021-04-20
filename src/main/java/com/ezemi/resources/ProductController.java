package com.ezemi.resources;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	

	@Autowired
	ProductService productService;
	
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

	
}
