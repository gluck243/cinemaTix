package com.gluck.cinemaTix.repository

import com.gluck.cinemaTix.model.*
import org.apache.el.stream.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository: JpaRepository<Movie, Long>
interface RoomRepository: JpaRepository<Room, Long>
interface SeatRepository: JpaRepository<Seat, Long>
interface ShowtimeRepository: JpaRepository<Showtime, Long>
interface BookingRepository: JpaRepository<Booking, Long> {
    fun existsBySeatAndShowtime(seat: Seat, showtime: Showtime): Boolean
}