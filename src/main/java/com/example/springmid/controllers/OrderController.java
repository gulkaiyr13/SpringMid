//package com.example.springmid.controllers;
//import com.example.springmid.services.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/order")
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping()
//    public ResponseEntity<OrderDTO> createOrder(@Validated @RequestBody OrderDTO newOrder) {
//        newOrder.setId(null);
//        OrderDTO savedOrder = orderService.saveOrder(newOrder);
//        return ResponseEntity.ok(savedOrder);
//    }
//
//
////    @GetMapping("/{id}")
////    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
////        // Retrieve the order from the service layer
////        Optional<OrderDTO> orderOptional = orderService.getOrderById(id);
////
////        // Check if the order exists
////        if (orderOptional.isPresent()) {
////            // Return the order along with HTTP status 200 (OK)
////            return ResponseEntity.ok(orderOptional.get());
////        } else {
////            // If the order is not found, return HTTP status 404 (Not Found)
////            return ResponseEntity.notFound().build();
////        }
////    }
//}
