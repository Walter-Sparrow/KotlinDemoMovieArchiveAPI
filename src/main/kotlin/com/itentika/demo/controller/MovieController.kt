package com.itentika.demo.controller

import com.itentika.demo.dto.MovieDto
import com.itentika.demo.dto.PageDto
import com.itentika.demo.entity.Movie
import com.itentika.demo.service.MovieService
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@AllArgsConstructor
@Controller
@RequestMapping("/movies")
class MovieController {
    private val movieService: MovieService? = null

    @GetMapping("/{id}")
    fun getMovieById(@PathVariable id: Long): ResponseEntity<Movie?> {
        return ResponseEntity(movieService!!.getById(id), HttpStatus.OK)
    }

    @GetMapping
    fun getMovies(pageDto: PageDto): ResponseEntity<Page<Movie>> {
        return ResponseEntity(movieService!!.getMovies(pageDto), HttpStatus.OK)
    }

    @PostMapping
    fun addMovie(@RequestBody movieDto: MovieDto): ResponseEntity<Movie?> {
        return ResponseEntity(movieService!!.addMovie(movieDto), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateMovie(@PathVariable id: Long, @RequestBody movieDto: MovieDto): ResponseEntity<Movie?> {
        return ResponseEntity(movieService!!.updateMovie(id, movieDto), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deleteById(@PathVariable id: Long) {
        movieService!!.deleteById(id)
    }
}