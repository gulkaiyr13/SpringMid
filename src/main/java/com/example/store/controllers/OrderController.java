package com.example.store.controllers;

import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.entities.User;
import com.example.store.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(
        name = "Controller of orders"
)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(
            summary = "Order creating"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO,
                                                        @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(orderService.create(orderRequestDTO, user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Order updating"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(orderService.update(id, orderRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Order getting by id"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    @Operation(
            summary = "Getting all orders of user"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrdersByUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(orderService.getAll(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Order deleting"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
