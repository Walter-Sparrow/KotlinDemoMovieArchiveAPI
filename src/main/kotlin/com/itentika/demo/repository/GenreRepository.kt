package com.itentika.demo.repository

import com.itentika.demo.entity.Genre
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository : JpaRepository<Genre, Long?> {
    fun existsByName(name: String?): Boolean
}