package com.itentika.demo.repository

import com.itentika.demo.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository
import java.sql.Date

interface MovieRepository : JpaRepository<Movie, Long> {
    fun existsByTitleAndReleaseDate(title: String, releaseDate: Date): Boolean
}