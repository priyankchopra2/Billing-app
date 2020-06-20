package com.example.webApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.webApp.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>  {
	
		Product findByName(String name);
}
