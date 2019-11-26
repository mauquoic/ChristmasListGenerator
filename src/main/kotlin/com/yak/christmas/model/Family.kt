package com.yak.christmas.model

import com.yak.christmas.model.Person

class Family(val name: String, var members: List<Person>) {

    fun assignGifts(otherFamilies: List<Family>){
        this.members.forEach { it.assignGift(otherFamilies.flatMap { family -> family.members }) }
    }

}