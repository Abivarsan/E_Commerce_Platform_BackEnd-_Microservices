package com.ecommerce.deliveryservice.service;

import com.ecommerce.deliveryservice.dto.AcceptDeliveryDto;
import com.ecommerce.deliveryservice.entity.Delivery;
import com.ecommerce.deliveryservice.entity.OrderList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeliveryService {

    public List<OrderList> getAvailableOrders();

    public List<Delivery> getAllOrdersOfCouriers();

    public Delivery getOrdersByCourier(String courierId);

    public ResponseEntity<Void> acceptDelivery(AcceptDeliveryDto acceptDeliveryDto);

    public ResponseEntity<Void> completeDelivery(AcceptDeliveryDto deliveryDto);
}
