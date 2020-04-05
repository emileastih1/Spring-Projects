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
package org.springframework.data.solr.showcase.repositories;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.data.solr.showcase.product.model.Product;

/**
 * @author Christoph Strobl
 */
public interface ProductRepository extends SolrCrudRepository<Product, String> {

	@Highlight(prefix = "<b>", postfix = "</b>")
	@Query(fields = { SearchableProductDefinition.ID_FIELD_NAME, SearchableProductDefinition.NAME_FIELD_NAME,
			SearchableProductDefinition.PRICE_FIELD_NAME, SearchableProductDefinition.FEATURES_FIELD_NAME,
			SearchableProductDefinition.AVAILABLE_FIELD_NAME }, defaultOperator = Operator.AND)
	HighlightPage<Product> findByNameIn(Collection<String> names, Pageable page);

	@Facet(fields = { SearchableProductDefinition.NAME_FIELD_NAME })
	FacetPage<Product> findByNameStartsWith(Collection<String> nameFragments, Pageable pagebale);

}
