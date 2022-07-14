package com.itentika.demo.repository

import com.itentika.demo.entity.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
    fun existsByName(name: String): Boolean
}