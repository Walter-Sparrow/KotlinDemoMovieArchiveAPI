package com.itentika.demo.service

import com.itentika.demo.dto.PageDto
import com.itentika.demo.dto.PersonDto
import com.itentika.demo.entity.Person
import com.itentika.demo.exception.ResourceAlreadyExistsException
import com.itentika.demo.exception.ResourceNotFoundException
import com.itentika.demo.repository.PersonRepository
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class PersonService {
    private val personRepository: PersonRepository? = null

    fun getById(id: Long): Person {
        return personRepository!!
            .findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    "The person with id=$id does not exist.")
            }
    }

    fun getPersons(pageDto: PageDto): Page<Person?> {
        return personRepository!!.findAll(pageDto.pageable)
    }

    fun addPerson(personDto: PersonDto): Person {
        if (personRepository!!.existsByName(personDto.getName())) {
            throw ResourceAlreadyExistsException("The person with such name already exists.")
        }

        val person = Person(personDto.getName())
        personRepository.save(person)
        return person
    }

    fun updatePerson(id: Long, personDto: PersonDto): Person {
        val person = personRepository!!.findById(id)
        if (!person.isPresent) {
            throw ResourceNotFoundException("The person with id=$id does not exist.")
        }

        val personFromDB = person.get()
        personFromDB.setName(personDto.getName())
        personRepository.save(personFromDB)
        return personFromDB
    }

    fun deleteById(id: Long) {
        personRepository!!.deleteById(id)
    }
}