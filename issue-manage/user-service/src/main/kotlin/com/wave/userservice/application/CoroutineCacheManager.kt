package com.wave.userservice.application

import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

@Component
class CoroutineCacheManager<T> {

    private val localCache = ConcurrentHashMap<String, CacheWrapper<T>>()

    suspend fun awaitPut(key: String, value: T, ttl: Duration) {
        localCache[key] = CacheWrapper(value, Instant.now().plusMillis(ttl.toMillis()))

    }

    fun awaitEvict(key: String) {
        localCache.remove(key)
    }

    suspend fun awaitGetOrPut(
        key: String,
        ttl: Duration? = Duration.ofMinutes(5),
        supplier: suspend () -> T
    ): T {
        val now = Instant.now()
        val cacheWrapper = localCache[key]

        val cached = if (cacheWrapper == null) {
            CacheWrapper(cached = supplier(), ttl = now.plusMillis(ttl!!.toMillis())).also {
                localCache[key] = it
            }
        } else if (now.isAfter(cacheWrapper.ttl)) {
            localCache.remove(key)
            CacheWrapper(cached = supplier(), ttl = now.plusMillis(ttl!!.toMillis())).also {
                localCache[key] = it
            }
        } else {
            cacheWrapper
        }

        checkNotNull(cached.cached)
        return cached.cached
    }


    data class CacheWrapper<T>(val cached: T, val ttl: Instant)
}