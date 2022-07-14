package com.itentika.demo.repository

import com.itentika.demo.entity.Movie
import com.itentika.demo.entity.MovieCast
import org.springframework.data.jpa.repository.JpaRepository

interface MovieCastRepository : JpaRepository<MovieCast, Long> {
    fun deleteAllByMovie(movie: Movie)
}