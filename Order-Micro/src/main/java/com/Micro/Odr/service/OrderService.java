package com.Micro.Odr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.Micro.Odr.common.Payment;
import com.Micro.Odr.common.TransactionRequest;
import com.Micro.Odr.common.TransactionResponse;
import com.Micro.Odr.entity.Order;
import com.Micro.Odr.proxyservice.PaymentFeingnClient;
import com.Micro.Odr.rigistory.OrderRepository;

@Service
public class OrderService {

	@Autowired
	PaymentFeingnClient feingnclient;
	@Autowired
	private OrderRepository repository;

	public TransactionResponse saveOrder(TransactionRequest request) {
		String response = "";
		repository.save(request.getOrder());
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		Payment paymentResponse = feingnclient.doPayment(payment);
		response = paymentResponse.getPaymentStatus().equals("success")
				? "payment processing successful and order placed"
				: "there is a failure in payment api , order added to cart";
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);

	}
	
	
	public Order updateOrder(int id, TransactionRequest request) {
		
		Order updatePayment = repository.findById(id);
		Order order= request.getOrder(); 

    	updatePayment.setPrice(order.getPrice());
    	
		return  repository.save(updatePayment);
	}
	
	  public TransactionResponse updatePaymentById(int orderId, TransactionRequest request ) {
	  
	   updateOrder(orderId, request);
	    	
	  String response = ""; 
	  repository.save(request.getOrder());
	  Order order= request.getOrder(); 
	  
	  Payment payment = request.getPayment();
	  payment.setOrderId(order.getId());
	  payment.setAmount(order.getPrice());
	  payment.setAmount(order.getPrice());
	  Payment paymentResponse = feingnclient.updatePayment(orderId, payment); 
	  response = paymentResponse.getPaymentStatus().equals("success") ?
	  "payment updated successful and order placed" :
	  "there is a failure in payment api , order updated to cart"; 
	
	  return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	  
	 
	  }
	  
     public List<Order> getAllPaymentHistory(){
	      
	 return repository.findAll();
	    
     }
     
     public Order findPaymentHistoryByOrderId(int orderId){
	      
	      //  logger.info("paymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
	        return  repository.findById(orderId);
	}
     
     public void deletePayment(int orderId) {
    	 
					
	  repository.deleteById(orderId); 
	  feingnclient.deletePayment(orderId);
	 
     }
	 

	
}