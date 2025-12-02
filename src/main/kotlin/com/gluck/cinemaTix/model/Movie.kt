package com.gluck.cinemaTix.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table
class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val title: String,

    @Column(nullable = false)
    val duration: Int,

    @Column(nullable = false)
    val genre: String,

    @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL], orphanRemoval = true)
    val showtimes: MutableList<Showtime>
)