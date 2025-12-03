package com.gluck.cinemaTix.service

import com.gluck.cinemaTix.exceptions.InvalidBookingException
import com.gluck.cinemaTix.model.Booking
import com.gluck.cinemaTix.model.BookingRequest
import com.gluck.cinemaTix.repository.BookingRepository
import com.gluck.cinemaTix.repository.SeatRepository
import com.gluck.cinemaTix.repository.ShowtimeRepository
import org.springframework.stereotype.Service

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val showtimeRepository: ShowtimeRepository,
    private val seatRepository: SeatRepository
)
{

    fun bookTicket(request: BookingRequest): Booking {
        val showtime = showtimeRepository.findById(request.showTimeId).orElseThrow { InvalidBookingException("Showtime Id does not match available showtime details") }
        val seat = seatRepository.findById(request.seatId).orElseThrow { InvalidBookingException("Seat ID does not match available showtime details") }
        if (seat.room.id != showtime.room.id)
            throw InvalidBookingException("Seat is not found")


        if (bookingRepository.existsBySeatAndShowtime(seat, showtime))
            throw InvalidBookingException("Seat is taken. Choose a different seat and try again")


        return bookingRepository.save(Booking(showtime = showtime, seat = seat))

    }

}

