package com.example.api.service

import com.example.api.repository.CouponRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    lateinit var applyService: ApplyService

    @Autowired
    lateinit var couponRepository: CouponRepository

    @Test
    fun `한번만 응모`() {
        //given
        val userId = 1L
        applyService.apply(userId)
        //when
        val count = couponRepository.count()

        //then
        assertThat(count).isEqualTo(1)
    }

    @Test
    fun `여러명 응모`() {
        var threadCount = 1000

        val executorService = Executors.newFixedThreadPool(threadCount)
        val countDownLatch = CountDownLatch(threadCount)
        for (i in 0..threadCount) {
            val userId: Long = i.toLong()
            executorService.submit {
                try {
                    applyService.apply(userId)
                } finally {
                    countDownLatch.countDown()
                }
            }
        }
        countDownLatch.await()

        couponRepository.count().let {
            assertThat(it).isEqualTo(100)
        }

    }
}