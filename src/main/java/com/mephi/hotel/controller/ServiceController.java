package com.mephi.hotel.controller;

import com.mephi.hotel.model.*;
import com.mephi.hotel.postRequestResponse.response.MessageResponse;
import com.mephi.hotel.postRequestResponse.response.OrderResponse;
import com.mephi.hotel.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
public class ServiceController {
    private final ServiceService serviceService;
    private final BookingRoomService bookingRoomService;
    private final OrderService orderService;

    public ServiceController(ServiceService serviceService, BookingRoomService bookingRoomService, OrderService orderService) {
        this.serviceService = serviceService;
        this.bookingRoomService = bookingRoomService;
        this.orderService = orderService;
    }

    @GetMapping("/order/service")
    public List<Service> getService() {
        return serviceService.getAllService().collect(Collectors.toList());
    }

    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> saveOrder(@RequestBody OrderResponse orderResponse) {
        try {
            Order order = new Order();
            order.setTotal_price(orderResponse.getTotal_price());
            BookingRoom bookingRoom = bookingRoomService.getBookingRoomById(Long.parseLong(orderResponse.getBooking_room()));
            order.setBooking_room(bookingRoom);

            for (Integer s : orderResponse.getService()) {

                Service service = serviceService.getServiceById(new Long(s));
                service.getOrder().add(order);
                order.getServices().add(service);

                orderService.save(order);
            }
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            return ResponseEntity.badRequest().body(new MessageResponse(strEx));
        }
    }

    @GetMapping("/order/{id}")
    public Order getById(@PathVariable(value = "id") Long id) {
        return orderService.getOrderById(id);
    }
}
