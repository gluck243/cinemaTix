package com.gluck.cinemaTix.controller

import com.gluck.cinemaTix.model.BookingRequest
import com.gluck.cinemaTix.service.BookingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class BookingController(private val bookingService: BookingService) {
    @PostMapping("/bookings")
    fun bookTicket(@RequestBody booking: BookingRequest): Long {
        return bookingService.bookTicket(booking).id
    }

}