package com.wave.userservice.model

import com.wave.userservice.domain.User
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

data class UserEditRequest (
    val username : String,
)

data class MeResponse(
    val id : Long,
    val profileUrl : String?,
    val email : String,
    val username : String,
    val createdAt : LocalDateTime?,
    val updatedAt : LocalDateTime?,
) {
    companion object {
        operator fun invoke(user : User) = with(user) {
            MeResponse(
                id = id!!,
                profileUrl = if(profileUrl.isNullOrEmpty()) null else "http://localhost:9090/images",
                email = email,
                username = username,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }
}