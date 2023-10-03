package com.example.productsservice.query;

import com.example.productsservice.command.rest.ProductRestModel;
import com.example.productsservice.core.data.ProductEntity;
import com.example.productsservice.core.data.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsQueryHandler {

    private final ProductRepository productRepository;

    public ProductsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductQuery query) {
        List<ProductRestModel> productsRest = new ArrayList<>();
        List<ProductEntity> storedProducts = productRepository.findAll();
        for (ProductEntity productEntity : storedProducts) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productsRest.add(productRestModel);
        }
        return productsRest;
    }
}
