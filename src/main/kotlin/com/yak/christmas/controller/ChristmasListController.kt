package com.yak.christmas.controller

import com.yak.christmas.business.ChristmasListBC
import com.yak.christmas.model.Present
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class ChristmasListController @Autowired constructor(val christmasListBC: ChristmasListBC) {

    @PostMapping("/api/create-lists", produces = ["application/json"])
    fun determineLists(): ResponseEntity<String> {
        christmasListBC.determineLists()
        return ResponseEntity.ok("The family lists have been prepared! Have fun!")
    }

    @GetMapping("/api/persons/{personId}/draw-person", produces = ["application/json"])
    fun retrieveGiftee(@PathVariable("personId") personId: String): ResponseEntity<String> {
        return ResponseEntity.ok(christmasListBC.findPersonById(personId)!!.giftPersonName!!)
    }

    @GetMapping("/api/persons/{personId}/presents", produces = ["application/json"])
    fun retrieveOwnGiftList(@PathVariable("personId") personId: String): ResponseEntity<List<Present>> {
        return ResponseEntity.ok(christmasListBC.findPersonById(personId)!!.wantedPresents)
    }

    @GetMapping("/api/persons/{personId}/presents-to-buy", produces = ["application/json"])
    fun retrieveGifteeGiftList(@PathVariable("personId") personId: String): ResponseEntity<List<Present>> {
        return ResponseEntity.ok(christmasListBC.findPersonById(personId)!!.giftPerson!!.wantedPresents)
    }

    @GetMapping("/api/persons/{personId}/gift-person-code", produces = ["application/json"])
    fun retrieveGifteeGiftListCode(@PathVariable("personId") personId: String): ResponseEntity<String> {
        return ResponseEntity.ok(christmasListBC.findPersonById(personId)!!.giftPerson!!.id)
    }

    @PostMapping("/api/persons/{personId}/presents", consumes = ["application/json"], produces = ["application/json"])
    fun addPresent(@PathVariable("personId") personId: String, @RequestBody present: Present): ResponseEntity<List<Present>> {
        return ResponseEntity.ok(christmasListBC.addPresent(personId, present))
    }
}