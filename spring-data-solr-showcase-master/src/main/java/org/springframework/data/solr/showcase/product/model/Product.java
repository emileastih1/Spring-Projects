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
package org.springframework.data.solr.showcase.product.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.geo.GeoLocation;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.data.solr.showcase.product.SearchableProductDefinition;

/**
 * @author Christoph Strobl
 */
@SolrDocument(solrCoreName = "collection1")
public class Product implements SearchableProductDefinition {

	private @Id @Indexed String id;

	private @Indexed(NAME_FIELD_NAME) List<String> name;

	private @Indexed(AVAILABLE_FIELD_NAME) List<Boolean> available;

	private @Indexed List<String> features;

	private @Indexed(PRICE_FIELD_NAME) List<Float> price;

	private @Indexed(CATEGORIES_FIELD_NAME) List<String> categories;

	private @Indexed List<Integer> popularity;

	private @Indexed(LOCATION_FIELD_NAME) List<GeoLocation> location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}


	public List<Boolean> getAvailable() {
		return available;
	}

	public void setAvailable(List<Boolean> available) {
		this.available = available;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public List<Float> getPrice() {
		return price;
	}

	public void setPrice(List<Float> price) {
		this.price = price;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<Integer> getPopularity() {
		return popularity;
	}

	public void setPopularity(List<Integer> popularity) {
		this.popularity = popularity;
	}

	public List<GeoLocation> getLocation() {
		return location;
	}

	public void setLocation(List<GeoLocation> location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}

}
