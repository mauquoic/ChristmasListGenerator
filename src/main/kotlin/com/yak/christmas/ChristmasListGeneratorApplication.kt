package com.yak.christmas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.boot.builder.SpringApplicationBuilder



@SpringBootApplication(scanBasePackages = ["com.yak.christmas.config",
    "com.yak.christmas.controller",
    "com.yak.christmas.business",
    "com.yak.christmas.ui"])

class ChristmasListGeneratorApplication
    : SpringBootServletInitializer()
{
//    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
//        return application.sources(ChristmasListGeneratorApplication::class.java!!)
//    }
}
fun main(args: Array<String>) {
    runApplication<ChristmasListGeneratorApplication>(*args)
}
