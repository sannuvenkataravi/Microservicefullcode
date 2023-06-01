package com.Micro.Odr.proxyservice;

import org.apache.http.HttpStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Micro.Odr.common.Payment;

@FeignClient(name = "PAYMENT-MICROSERVICES")
public interface PaymentFeingnClient {

	
	 @PostMapping(path="/payment/doPayment")
	  Payment doPayment(@RequestBody Payment payment);
	
	  @PutMapping(path="/payment/{orderId}")
	  Payment updatePayment(@PathVariable int orderId,@RequestBody Payment paymentDetails);

	  @DeleteMapping(path="/payment/{orderId}")
	   ResponseEntity<HttpStatus> deletePayment(@PathVariable int orderId);
	  
}

