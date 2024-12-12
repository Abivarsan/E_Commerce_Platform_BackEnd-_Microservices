package com.ecommerce.trackingservice.repository;


import com.ecommerce.trackingservice.Enum.OrderStatus;
import com.ecommerce.trackingservice.entity.TrackingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository extends JpaRepository<TrackingInfo, String> {
    List<TrackingInfo> findByOrderStatus(OrderStatus orderStatus);
}
