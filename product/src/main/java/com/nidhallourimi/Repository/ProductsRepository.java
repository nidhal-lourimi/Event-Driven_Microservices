package com.nidhallourimi.Repository;

import com.nidhallourimi.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity,String> {

    ProductEntity findByProductId(String productId);
}
