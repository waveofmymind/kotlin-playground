package com.example.relational.expert

import com.example.relational.user.User
import jakarta.persistence.*
import org.hibernate.annotations.LazyToOne
import org.hibernate.annotations.LazyToOneOption

@Entity
class Expert(

    @Id
    var id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "users_id")
    val user : User,

    val career : String,
) {

    fun createExpert(request: ExpertRegister,user : User) : Expert {
        return Expert(
            career = request.career,
            user = user
        )
    }

}