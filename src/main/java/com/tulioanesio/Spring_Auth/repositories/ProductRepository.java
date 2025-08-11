package com.tulioanesio.Spring_Auth.repositories;

import com.tulioanesio.Spring_Auth.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
