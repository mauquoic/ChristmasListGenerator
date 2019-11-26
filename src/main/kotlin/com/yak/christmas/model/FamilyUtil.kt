package com.yak.christmas.model

import com.yak.christmas.model.Person

class FamilyUtil {
    companion object {

        fun assignFamilyGifts(families: List<Family>){
            families.sortedByDescending { it.members.size }
                .forEach { family ->
                family.assignGifts(families.filter { otherFamily -> otherFamily != family }) }
        }

        fun createFamilies(): List<Family> {
            val bertrange =
                Family(
                    "bertrange", listOf(
                        Person("Cedric", "ced123"),
                        Person("Slava", "sla456"),
                        Person("Laurens", "lau789"),
                        Person("Corry", "cor321"),
                        Person("Ria", "ria654"),
                        Person("Rudi", "rud987")
                    )
                )

            val lint =
                Family(
                    "lint", listOf(
                        Person("Dirk", "dir147"),
                        Person("Natalie", "nat258"),
                        Person("Ebe", "ebe369"),
                        Person("Korre", "kor741")
                    )
                )

            val kontichBruno =
                Family(
                    "kontichBruno", listOf(
                        Person("Bruno", "bru159"),
                        Person("Annemie", "ann357")
                    )
                )

            val kontichIve =
                Family(
                    "kontichIve", listOf(
                        Person("Ive", "ive429"),
                        Person("Yily", "yil861"),
                        Person("Luca", "luc267"),
                        Person("Bipo", "bip483")
                    )
                )

            val mammie =
                Family(
                    "mammie", listOf(
                        Person("Mammie", "mammie")
                    )
                )

            return listOf(bertrange, mammie, lint, kontichBruno, kontichIve)
        }
    }
}