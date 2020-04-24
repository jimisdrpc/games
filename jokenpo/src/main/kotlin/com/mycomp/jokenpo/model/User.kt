package com.mycomp.jokenpo.model

import javax.persistence.*


@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(nullable = false)
        val name: String
)