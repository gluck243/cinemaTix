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
class Room(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false)
    val capacity: Int,

    @OneToMany(mappedBy = "room", cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: MutableList<Seat>
)
