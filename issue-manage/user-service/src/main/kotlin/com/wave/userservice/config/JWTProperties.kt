package com.wave.userservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "jwt")
data class JWTProperties(
    @Value("\${jwt.issuer}")
    val issuer: String,
    @Value("\${jwt.subject}")
    val subject: String,
    @Value("\${jwt.audience}")
    val expiresTime:Long,
    @Value("\${jwt.secret}")
    val secret: String,

)