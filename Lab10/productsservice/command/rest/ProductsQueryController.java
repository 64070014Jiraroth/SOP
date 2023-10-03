package com.example.productsservice.command.rest;


import com.example.productsservice.query.FindProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {
    private final QueryGateway queryGateway;

    public ProductsQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        return queryGateway.query(
                findProductQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class)
        ).join();
    }
}
