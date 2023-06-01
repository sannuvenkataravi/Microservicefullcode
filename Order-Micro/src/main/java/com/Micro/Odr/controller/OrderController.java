package com.Micro.Odr.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Micro.Odr.common.TransactionRequest;
import com.Micro.Odr.common.TransactionResponse;
import com.Micro.Odr.entity.Order;
import com.Micro.Odr.service.OrderService;



/**
 * @author 10714851
 *
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins ="*")
public class OrderController {

	@Autowired
	private OrderService service;
	
	
	    @PostMapping("/bookOrder")
	    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
	        return service.saveOrder(request);
	    }
	 


		  @PutMapping("{orderId}") public TransactionResponse
		  updatePaymentById(@PathVariable int orderId,@RequestBody TransactionRequest
		  request) {
		  
		  return service.updatePaymentById(orderId, request ); 
		  
		  }
		 
		  @GetMapping("{orderId}")
		    public Order findPaymentHistoryByOrderId(@PathVariable int orderId){
		              return service.findPaymentHistoryByOrderId(orderId);
		    }
		  
		  @GetMapping
		    public List<Order> getAllPaymentHistory(){
		       // return employeeRepository.findAll();
		    	 return service.getAllPaymentHistory();
		    }


		    @DeleteMapping("{orderId}")
		    public void deletePayment(@PathVariable int orderId){
		    	service.deletePayment(orderId);
		    }
		 
		 
		
}
