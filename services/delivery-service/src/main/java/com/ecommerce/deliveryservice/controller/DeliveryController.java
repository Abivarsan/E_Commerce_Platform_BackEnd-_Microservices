package com.ecommerce.deliveryservice.controller;


import com.ecommerce.deliveryservice.dto.AcceptDeliveryDto;
import com.ecommerce.deliveryservice.entity.Delivery;
import com.ecommerce.deliveryservice.entity.OrderList;
import com.ecommerce.deliveryservice.service.DeliveryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryServiceImp deliveryService;

    @GetMapping("/availableOrders")
    public List<OrderList> getAvailableOrders(){
        return this.deliveryService.getAvailableOrders();
    }

    @GetMapping("/couriers")
    public List<Delivery> getAllOrdersOfCouriers(){
        return this.deliveryService.getAllOrdersOfCouriers();
    }

    @GetMapping("/courier/{courierId}")
    public Delivery getOrdersByCourier(@PathVariable String courierId){
        return this.deliveryService.getOrdersByCourier(courierId);
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptDelivery (@RequestBody AcceptDeliveryDto acceptDeliveryDto){
        return this.deliveryService.acceptDelivery(acceptDeliveryDto);
    }

    @PutMapping("/complete")
    public ResponseEntity<Void> completeDelivery(@RequestBody AcceptDeliveryDto acceptDeliveryDto){
        return this.deliveryService.completeDelivery(acceptDeliveryDto);
    }
}
