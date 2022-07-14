package com.itentika.demo.controller

import com.itentika.demo.dto.PageDto
import com.itentika.demo.dto.PersonDto
import com.itentika.demo.entity.Person
import com.itentika.demo.service.PersonService
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@AllArgsConstructor
@RequestMapping("/persons")
class PersonController {
    private val personService: PersonService? = null

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: Long): ResponseEntity<Person?> {
        return ResponseEntity(personService!!.getById(id), HttpStatus.OK)
    }

    @GetMapping
    fun getPersons(pageDto: PageDto): ResponseEntity<Page<Person?>> {
        return ResponseEntity(personService!!.getPersons(pageDto), HttpStatus.OK)
    }

    @PostMapping
    fun addPerson(@RequestBody personDto: PersonDto): ResponseEntity<Person?> {
        return ResponseEntity(personService!!.addPerson(personDto), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id: Long, @RequestBody personDto: PersonDto): ResponseEntity<Person?> {
        return ResponseEntity(personService!!.updatePerson(id, personDto), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deleteById(@PathVariable id: Long) {
        personService!!.deleteById(id)
    }
}