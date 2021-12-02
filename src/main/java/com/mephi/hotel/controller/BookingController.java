package com.mephi.hotel.controller;

import com.mephi.hotel.model.*;
import com.mephi.hotel.postRequestResponse.response.BookingResponse;
import com.mephi.hotel.postRequestResponse.response.MessageResponse;
import com.mephi.hotel.postRequestResponse.response.PaymentResponse;
import com.mephi.hotel.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@RestController
public class BookingController {
    private final BookingService bookingService;
    private final BookingRoomService bookingRoomService;
    private final RoomService roomService;
    private final UserService userService;
    private final PaymentService paymentService;

    public BookingController(BookingService bookingService, BookingRoomService bookingRoomService, RoomService roomService, UserService userService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.bookingRoomService = bookingRoomService;
        this.roomService = roomService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @GetMapping("/booking")
    public List<Booking> getBookings() {
        return bookingService.getAllBooking().collect(Collectors.toList());
    }

    @GetMapping("/booking/{id}")
    public Booking getById(@PathVariable(value = "id") Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping(value = "/booking/room/")
    public List<Room> getAvailableRoom(@RequestParam Map<String, String> requestParams) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date start_date = formatter.parse(requestParams.get("start_date"));
        Date end_date = formatter.parse(requestParams.get("end_date"));
        List<Room> unavailableRooms =
                roomService.getAllUnavailableRoom(start_date, end_date);
        List<Room> rooms = roomService.getAllRoom().collect(Collectors.toList());
        if (!unavailableRooms.isEmpty())
            for (Room r : unavailableRooms)
                if (rooms.contains(r))
                    rooms.remove(r);
        return rooms;
    }
    @PostMapping(value = "/payment", consumes = {"application/json"})
    public ResponseEntity<?> savePaymentCheckIn(@RequestBody PaymentResponse paymentResponse) {
        try {
            Payment payment = new Payment();
            payment.setPayment_type(paymentResponse.getPayment_type());
            payment.setAmount(paymentResponse.getAmount());
            Booking booking = bookingService.getBookingById(Long.parseLong(paymentResponse.getBooking()));
            booking.setStatus("Зарегистрировано");
            booking.setCheck_in(new Date());
            payment.setBooking(booking);
            paymentService.save(payment);

            return ResponseEntity.status(HttpStatus.OK).body(payment);
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            return ResponseEntity.badRequest().body(new MessageResponse(strEx));
        }
    }

    @PostMapping(value = "/payment/out", consumes = {"application/json"})
    public ResponseEntity<?> savePaymentCheckOut(@RequestBody PaymentResponse paymentResponse) {
        try {
            Payment payment = new Payment();
            payment.setPayment_type(paymentResponse.getPayment_type());
            payment.setAmount(paymentResponse.getAmount());
            Booking booking = bookingService.getBookingById(Long.parseLong(paymentResponse.getBooking()));
            booking.setStatus("Номер освобожден");
            booking.setCheck_out(new Date());
            payment.setBooking(booking);
            paymentService.save(payment);

            return ResponseEntity.status(HttpStatus.OK).body(payment);
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            return ResponseEntity.badRequest().body(new MessageResponse(strEx));
        }
    }

    @PostMapping(value = "/booking", consumes = {"application/json"})
    public ResponseEntity<?> saveBooking(@RequestBody BookingResponse bookingResponse) {
        try {
            Booking booking = new Booking();
            booking.setEnd_date(bookingResponse.getEnd_date());
            booking.setStart_date(bookingResponse.getStart_date());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(bookingResponse.getEnd_date());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(bookingResponse.getStart_date());
            booking.setNight((int) daysBetween(cal2, cal1));

            User user = userService.findUserById(Long.parseLong(bookingResponse.getUser()));
            booking.setUser(user);

            booking.setStatus("Забронировано");

            for (Integer r : bookingResponse.getRoom()) {
                BookingRoom bookingRoom = new BookingRoom();
                bookingRoom.setBooking(booking);
                Room room = roomService.getRoomById(new Long(r));
                bookingRoom.setRoom(room);
                bookingRoom.setPrice(room.getPrice() * booking.getNight());

                room.getDetail().add(bookingRoom);
                booking.getDetail().add(bookingRoom);

                bookingService.save(booking);
            }
            return ResponseEntity.status(HttpStatus.OK).body(booking);
        } catch (Exception ex) {
            String strEx = ex.getCause().getCause().getMessage();
            return ResponseEntity.badRequest().body(new MessageResponse(strEx));
        }
    }

    /**
     * Using Calendar - THE CORRECT WAY
     **/
    public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }
}
