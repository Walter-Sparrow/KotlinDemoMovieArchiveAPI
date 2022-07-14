package com.itentika.demo.controller

import com.itentika.demo.dto.GenreDto
import com.itentika.demo.dto.PageDto
import com.itentika.demo.entity.Genre
import com.itentika.demo.service.GenreService
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@AllArgsConstructor
@RequestMapping("/genres")
class GenreController {
    private val genreService: GenreService? = null

    @GetMapping("/{id}")
    fun getGenreById(@PathVariable id: Long): ResponseEntity<Genre> {
        return ResponseEntity(genreService!!.getById(id), HttpStatus.OK)
    }

    @GetMapping
    fun getGenres(pageDto: PageDto): ResponseEntity<Page<Genre?>> {
        return ResponseEntity(genreService!!.getGenres(pageDto), HttpStatus.OK)
    }

    @PostMapping
    fun addGenre(@RequestBody genreDto: GenreDto): ResponseEntity<Genre> {
        return ResponseEntity(genreService!!.addGenre(genreDto), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateGenre(@PathVariable id: Long, @RequestBody genreDto: GenreDto): ResponseEntity<Genre> {
        return ResponseEntity(genreService!!.updateGenre(id, genreDto), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    fun deleteById(@PathVariable id: Long) {
        genreService!!.deleteById(id)
    }
}