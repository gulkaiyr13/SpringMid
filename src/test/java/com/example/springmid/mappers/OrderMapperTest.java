package com.example.springmid.mappers;

import com.example.springmid.dto.OrderDTO;
import com.example.springmid.entities.Order;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMapperTest {

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void testOrderDTOToOrderMapping() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1L);
        orderDTO.setOrderDate("2024-03-22");

        Order order = orderMapper.orderDTOToOrder(orderDTO);

        assertEquals(orderDTO.getOrderId(), order.getId());
        assertEquals(orderDTO.getOrderDate(), order.getOrderDate().toString());
    }

    @Test
    void testOrderToOrderDTOMapping() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(java.sql.Date.valueOf("2024-03-22"));

        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);

        assertEquals(order.getId(), orderDTO.getOrderId());
        assertEquals(order.getOrderDate().toString(), orderDTO.getOrderDate());
    }
}
