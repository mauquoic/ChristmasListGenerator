package com.yak.christmas.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

class Person(val name: String, val id: String, var hasAlreadyDrawn: Boolean = false) {
    @JsonIgnore
    var giftPerson: Person? = null

    @JsonIgnore
    var alreadyHasGift = false

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var wantedPresents : MutableList<Present> = mutableListOf()

    @JsonIgnore
    var giftPersonName: String? = null

    private fun assignPerson(person: Person){
        this.giftPerson = person
        this.giftPersonName = person.name
        person.alreadyHasGift = true
    }

    fun assignGift(people: List<Person>) {
        assignPerson(people.filter { !it.alreadyHasGift }.random())
    }

    fun addPresent(present: Present){
        wantedPresents.add(present)
    }

    fun printGift(): String {
        return "${this.name} koopt voor ${giftPerson!!.name}! \n"
    }
}