package com.wavlog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WavlogApplication

fun main(args: Array<String>) {
    runApplication<WavlogApplication>(*args)
}
