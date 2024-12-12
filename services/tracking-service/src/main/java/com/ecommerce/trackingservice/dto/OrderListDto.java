package com.ecommerce.trackingservice.dto;

import com.ecommerce.trackingservice.Enum.OrderStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDto {

    @Id
    private String orderNumber;

    private OrderStatus orderStatus;
}
