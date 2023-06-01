package com.micro.pmt.controller;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.pmt.entity.Payment;
import com.micro.pmt.repository.PaymentRepository;
import com.micro.pmt.services.PaymentService;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins ="*")
public class PaymentController {

    @Autowired
    private PaymentService service;
    @Autowired
    private PaymentRepository repository;

   
    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) {
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId){
              return service.findPaymentHistoryByOrderId(orderId);
    }

    @GetMapping
    public List<Payment> getAllPaymentHistory(){
       // return employeeRepository.findAll();
    	 return service.getAllPaymentHistory();
    }
    
    
    @PutMapping("{orderId}")
    public Payment updatePayment(@PathVariable int orderId,@RequestBody Payment paymentDetails) {
    	
          return service.updatePayment(orderId , paymentDetails);
    }
    

    @DeleteMapping("{orderId}")
    public void deletePayment(@PathVariable int orderId){

    	Payment payment = repository.findByOrderId(orderId);
                
    	repository.delete(payment);


    }
    
}