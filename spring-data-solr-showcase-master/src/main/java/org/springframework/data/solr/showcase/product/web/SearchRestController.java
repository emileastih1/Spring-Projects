package org.springframework.data.solr.showcase.product.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.data.solr.showcase.product.ProductRepository;
import org.springframework.data.solr.showcase.product.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchRestController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/restSearch")
	public Iterable<Product> search() {
		SolrResultPage<Product> page = (SolrResultPage<Product>) productRepository.findAll();
//		Product prod = productRepository.findOne("SP2514N");
//		List<Product> products = new ArrayList<Product>();
//		products.add(prod);
		
		List<Product> products = page.getContent();
		
		for (Product product : products) {
			System.out.println(" ------------------------------------------------------------ ");
			System.out.println("id:"+ 			product.getId());
			System.out.println("Name:"+ 		Arrays.toString(product.getName() == null ? new String [] {} : product.getName().toArray()));
			System.out.println("In Stock:"+		Arrays.toString(product.getAvailable() == null ? new String [] {} : product.getAvailable().toArray()));
			System.out.println("Store:"+ 		Arrays.toString(product.getLocation() == null ? new String [] {} : product.getLocation().toArray()));
			System.out.println("Categories:"+ 	Arrays.toString(product.getCategories() == null ? new String [] {} : product.getCategories().toArray()));
			System.out.println("Features:"+		Arrays.toString(product.getFeatures() == null ? new String [] {} : product.getFeatures().toArray()));
			System.out.println("Popularity:"+ 	Arrays.toString(product.getPopularity() == null ? new String [] {} : product.getPopularity().toArray()));
			System.out.println("Price:"+ 		Arrays.toString(product.getPrice() == null ? new String [] {} : product.getPrice().toArray()));
			System.out.println(" ------------------------------------------------------------ ");
		}
		
		
		return products;
	}
}
