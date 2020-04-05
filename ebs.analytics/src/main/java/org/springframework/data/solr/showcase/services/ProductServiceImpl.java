/*
 * Copyright 2012 - 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.solr.showcase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.data.solr.showcase.product.model.Product;
import org.springframework.data.solr.showcase.repositories.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * @author EAS
 */
@Service
class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		SolrResultPage<Product> page = (SolrResultPage<Product>) productRepository.findAll();
		List<Product> products = page.getContent();
		/*
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
		*/
		return products;
	}

	@Override
	public Product findById(String id) {
		return productRepository.findOne(id);
	}

}
