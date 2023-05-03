package com.wave.webfluxexample.runBlocking

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    doSomething()
}

fun printHello() = println("hello")

suspend fun doSomething() = coroutineScope {
    launch {
        println("world!")
    }

    launch {
        printHello()
    }
}