package org.springframework.data.solr.showcase.services;

import org.springframework.data.solr.showcase.product.model.Product;

public interface ProductService {

	int DEFAULT_PAGE_SIZE = 3;

	Iterable<Product> findAll();

	Product findById(String id);

}

