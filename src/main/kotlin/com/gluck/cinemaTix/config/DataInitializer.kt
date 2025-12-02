package com.gluck.cinemaTix.config

import com.gluck.cinemaTix.model.*
import com.gluck.cinemaTix.repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DataInitializer(
    private val movieRepository: MovieRepository,
    private val roomRepository: RoomRepository,
    private val seatRepository: SeatRepository,
    private val showtimeRepository: ShowtimeRepository
): CommandLineRunner {
    override fun run(vararg args: String?) {
        if (roomRepository.count() > 0) return

        val imaxRoom = roomRepository.save(Room(name = "IMAX Hall", capacity = 50))
        val specialRoom = roomRepository.save(Room(name = "Special Hall", capacity = 6))
        val oldRoom = roomRepository.save(Room(name = "Old Hall", capacity = 15))

        val seatsImax = mutableListOf<Seat>()
        val seatsSpecial = mutableListOf<Seat>()
        val seatsOld = mutableListOf<Seat>()

        val movieList = listOf(Movie(title = "Inception", genre = "Sci-Fi", duration = 148),
                Movie(title = "The Godfather", genre = "Drama", duration = 175),
                Movie(title = "Forrest Gump", genre = "Drama", duration = 142),
                Movie(title = "The Dark Knight", genre = "Action", duration = 152),
                Movie(title = "Titanic", genre = "Drama", duration = 195),
                Movie(title = "Star Wars: A New Hope", genre = "Sci-Fi", duration = 121))

        for (row in 'A'..'E') {
            for (seat in 1..10) {
                seatsImax.add(Seat(row = row, number = seat, room = imaxRoom))
            }
        }

        for (row in 'A'..'B') {
            for (seat in 1..3) {
                seatsSpecial.add(Seat(row = row, number = seat, room = specialRoom))
            }
        }

        for (row in 'A'..'C') {
            for (seat in 1..5) {
                seatsOld.add(Seat(row = row, number = seat, room = oldRoom))
            }
        }

        seatRepository.apply {
            saveAll(seatsImax)
            saveAll(seatsSpecial)
            saveAll(seatsOld)
        }

        val savedMovies = movieRepository.saveAll(movieList)

        showtimeRepository.apply {
            save(Showtime(movie = savedMovies[1], room = imaxRoom, startTime = LocalDateTime.now().plusDays(1)))
            save(Showtime(movie = savedMovies[5], room = oldRoom, startTime = LocalDateTime.now().minusDays(3)))
            save(Showtime(movie = savedMovies[4], room = specialRoom, startTime = LocalDateTime.now().minusDays(1)))
        }
    }
}