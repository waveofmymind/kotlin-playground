package com.wave.userservice.application

import com.wave.userservice.config.JWTProperties
import com.wave.userservice.domain.User
import com.wave.userservice.domain.UserRepository
import com.wave.userservice.exception.PasswordNotMatchesException
import com.wave.userservice.exception.UserExistsException
import com.wave.userservice.exception.UserNotFoundException
import com.wave.userservice.model.SignInRequest
import com.wave.userservice.model.SignInResponse
import com.wave.userservice.model.SignUpRequest
import com.wave.userservice.utils.BCryptUtils
import com.wave.userservice.utils.JWTClaim
import com.wave.userservice.utils.JWTUtils
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class UserService(
    private val userRepository : UserRepository,
    private val jwtProperties: JWTProperties,
    private val cacheManager: CoroutineCacheManager<User>,
) {

    companion object{
        private  val CACHE_TTL= Duration.ofMinutes(1)
    }
    suspend fun signUp(signUpRequest: SignUpRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserExistsException()
            }
            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username,
            )
            userRepository.save(user)
        }
    }

    suspend fun signIn(signInRequest: SignInRequest) {
        with(userRepository.findByEmail(signInRequest.email)?: throw UserNotFoundException()) {
            val verified = BCryptUtils.verify(signInRequest.password, password)
            if (!verified) {
                throw PasswordNotMatchesException()
            }

            val jwtClaim = JWTClaim(
                userId = id!!,
                email = email,
                username = username,
                profileUrl = profileUrl,
            )

            val token = JWTUtils.createToken(jwtClaim, jwtProperties)

            cacheManager.awaitPut(key=token, value = this,ttl = CACHE_TTL)

            SignInResponse(
                email = email,
                username = username,
                accessToken = token,
            )

        }
    }

    suspend fun logout(token: String) {
        cacheManager.awaitEvict(key = token)
    }
}