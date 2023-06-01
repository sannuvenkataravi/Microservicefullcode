package com.Micro.Odr.rigistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Micro.Odr.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

	Order findById(int orderId);
	
	List<Order> findAll();
	
	//void deleteByOrderId(int orderId ) ;
	
	
}
