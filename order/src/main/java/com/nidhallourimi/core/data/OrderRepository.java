package com.nidhallourimi.core.data;

import com.nidhallourimi.core.data.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,String> {

}
