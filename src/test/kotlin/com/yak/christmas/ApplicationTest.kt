package com.yak.christmas

import com.yak.christmas.model.FamilyUtil
import com.yak.christmas.model.FamilyUtil.Companion.assignFamilyGifts
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ApplicationTest {

    @Test
    fun main() {

        val families = FamilyUtil.createFamilies()
        assignFamilyGifts(families)

        println("Gegenereerde lijst van schenkers. \n")
        println("Powered by Yakman!! \n\n\n")


        families.flatMap { it.members }
            .forEach { println(it.printGift()) }
    }
}