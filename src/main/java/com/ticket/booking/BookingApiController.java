package com.ticket.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.booking.bo.BookingBO;
import com.ticket.booking.domain.BookingView;
import com.ticket.pay.bo.PayBO;
import com.ticket.pay.domain.Pay;

@RequestMapping("/api/bookings")
@RestController
public class BookingApiController {

    @Autowired
    private BookingBO bookingBO;

    @Autowired
    private PayBO payBO;

    // GET /api/bookings?isBooked=1
    @GetMapping
    public ResponseEntity<Map<String, Object>> bookingList(
            @RequestParam(value = "isBooked", required = false) Integer isBooked,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }

        List<BookingView> bookingViewList = bookingBO.getBookingListByUserId(userId, isBooked);

        Map<String, Object> result = new HashMap<>();
        result.put("bookingViewList", bookingViewList);
        return ResponseEntity.ok(result);
    }

    // GET /api/bookings/{bookingId}
    @GetMapping("/{bookingId}")
    public ResponseEntity<Map<String, Object>> bookingDetail(
            @PathVariable int bookingId,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }

        BookingView bookingView = bookingBO.generateBookingViewBybookingId(bookingId);
        Pay pay = payBO.getPay(bookingView.getBooking().getId());

        Map<String, Object> result = new HashMap<>();
        result.put("booking", bookingView);
        result.put("pay", pay);
        return ResponseEntity.ok(result);
    }
}
