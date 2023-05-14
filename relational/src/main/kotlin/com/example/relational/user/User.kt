package com.example.relational.user

import com.example.relational.expert.Expert
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.LazyToOne
import org.hibernate.annotations.LazyToOneOption

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val username : String,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY
    , optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JsonIgnore
    val expert : Expert? = null,

    ) {

    fun createUser(request: UserRegister) : User {
        return User(
            username = request.username,
        )
    }

}