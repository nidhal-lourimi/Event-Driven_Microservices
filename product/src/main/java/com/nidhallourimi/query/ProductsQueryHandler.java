package com.nidhallourimi.query;

import com.nidhallourimi.core.data.ProductEntity;
import com.nidhallourimi.core.data.ProductsRepository;
import com.nidhallourimi.query.rest.ProductRestModule;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductsQueryHandler {
    private final ProductsRepository productsRepository;

    public ProductsQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }
    @QueryHandler
    public List<ProductRestModule> findProducts(FindProductsQuery findProductsQuery){
        List<ProductRestModule> productsRestList =new ArrayList<>();
        List<ProductEntity> storedProducts = productsRepository.findAll();
        for (ProductEntity productEntity:storedProducts) {
            ProductRestModule productsRestModule=new ProductRestModule();
            BeanUtils.copyProperties(productEntity,productsRestModule);
            productsRestList.add(productsRestModule);

        }
        return productsRestList;

    }
}
