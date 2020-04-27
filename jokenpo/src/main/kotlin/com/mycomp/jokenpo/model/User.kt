package com.mycomp.jokenpo.model

import com.mycomp.jokenpo.enums.PlayType
import javax.persistence.*


@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(nullable = false)
        val name: String,

        @Enumerated
        @Column(nullable = false)
        val play: PlayType

)