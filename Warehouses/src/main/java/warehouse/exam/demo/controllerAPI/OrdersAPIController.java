/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package warehouse.exam.demo.controllerAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.exam.demo.DAL.OrdersDAO;
import warehouse.exam.demo.model.Orders;
import warehouse.exam.demo.service.OrdersService;

/**
 *
 * @author LAPTOP123
 */
@RestController
@RequestMapping("/api/orders")
public class OrdersAPIController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersAPIController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<Orders> getOrderByCode(@PathVariable String orderCode) {
        Optional<Orders> order = ordersService.getOrderByCode(orderCode);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order) {
        Orders savedOrder = ordersService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/{orderCode}")
    public ResponseEntity<Orders> updateOrder(@PathVariable String orderCode, @RequestBody Orders order) {
        if (!ordersService.getOrderByCode(orderCode).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setOrderCode(orderCode);
        Orders updatedOrder = ordersService.saveOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/details/{orderCode}")
    public ResponseEntity<Orders> getOrderDetails(@PathVariable String orderCode) {
        Orders orderDetails = ordersService.getOrderByOrderCode(orderCode);
        if (orderDetails != null) {
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/process/{orderCode}")
    public ResponseEntity<Map<String, String>> confirmOrder(@PathVariable String orderCode) {
        try {
            // Gọi dịch vụ hoặc lớp xử lý để cập nhật trạng thái đơn hàng
            ordersService.confirmOrder(orderCode);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Đơn hàng đang chờ xác nhận.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Lỗi khi xác nhận đơn hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/complete/{orderCode}")
    public ResponseEntity<Map<String, String>> completeOrder(@PathVariable String orderCode) {
        try {
            // Gọi dịch vụ hoặc lớp xử lý để cập nhật trạng thái đơn hàng
            ordersService.completeOrder(orderCode);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hoàn thành đơn hàng.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Lỗi khi hoàn thành đơn hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/cancel/{orderCode}")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable String orderCode) {
        try {
            // Gọi dịch vụ hoặc lớp xử lý để cập nhật trạng thái đơn hàng
            ordersService.cancelOrder(orderCode);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Đã hủy đơn hàng thành công.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Lỗi khi hủy đơn hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
