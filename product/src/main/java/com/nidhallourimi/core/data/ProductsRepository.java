package com.nidhallourimi.core.data;

import com.nidhallourimi.core.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity,String> {

    ProductEntity findByProductId(String productId);
}
