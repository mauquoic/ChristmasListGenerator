package com.yak.christmas.business

import com.yak.christmas.model.Family
import com.yak.christmas.model.FamilyUtil
import com.yak.christmas.model.Person
import com.yak.christmas.model.Present
import org.springframework.stereotype.Service

@Service
class ChristmasListBC {
    var families: List<Family> = FamilyUtil.createFamilies()
    var presentCounter: Int = 1

    fun findPersonById(personId: String): Person? {
        return families.flatMap { it.members }.find { it.id == personId.toLowerCase() }
    }

    fun determineLists(): List<Family> {
        families[0].members[0].giftPersonName?.let { RuntimeException("The gifts have already been drawn!") }
        FamilyUtil.assignFamilyGifts(families)
        families.flatMap { it.members }
                .forEach { println(it.printGift()) }
        return families
    }

    fun addPresent(personId: String, present: Present): List<Present> {
        var person = findPersonById(personId)
        present.id = this.presentCounter
        person!!.addPresent(present)
        this.presentCounter += 1
        return person.wantedPresents
    }

    fun editPresent(editedPresent: Present) {
        val present = findPresentById(editedPresent.id)
        present?.let {
            it.presentName = editedPresent.presentName
            it.link = editedPresent.link
            it.description = editedPresent.description
        }
    }

    fun findPresentById(id: Int): Present? {
        return families.flatMap { it.members }.flatMap { it.wantedPresents }.find { it.id == id }
    }

    fun findPersonWhoWantsPresentWithId(id: Int): Person {
        return families.flatMap { it.members }.first {
            it.wantedPresents.any { present -> present.id == id }
        }
    }

    fun deletePresentById(presentId: Int) {
        val person = findPersonWhoWantsPresentWithId(presentId)
        person.wantedPresents = person.wantedPresents.filter { it.id != presentId }.toCollection(mutableListOf())
    }
}