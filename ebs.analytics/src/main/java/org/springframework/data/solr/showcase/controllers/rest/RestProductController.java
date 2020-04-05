package org.springframework.data.solr.showcase.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.showcase.product.model.Product;
import org.springframework.data.solr.showcase.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author EAS
 */

@RestController
public class RestProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/restSearch")
	public ResponseEntity<Iterable<Product>> search() {
		Iterable<Product> productList = productService.findAll();
		return new ResponseEntity<Iterable<Product>>(productList, HttpStatus.OK);
	}
}
