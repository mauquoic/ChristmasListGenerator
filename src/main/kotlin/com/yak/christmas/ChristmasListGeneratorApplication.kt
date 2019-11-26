package com.yak.christmas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.yak.christmas.config",
    "com.yak.christmas.controller",
    "com.yak.christmas.business",
    "com.yak.christmas.ui"])
class ChristmasListGeneratorApplication

fun main(args: Array<String>) {
    runApplication<ChristmasListGeneratorApplication>(*args)
}
