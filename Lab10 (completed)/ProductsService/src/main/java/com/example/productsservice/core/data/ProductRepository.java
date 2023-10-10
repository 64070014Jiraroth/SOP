package com.example.productsservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;

public interface ProductRepository extends JpaRepository<ProductEntity, Spring> {

    ProductEntity findByProductId(String productId);

    ProductEntity findByProductIdOrTitle(String productId, String title);
}
