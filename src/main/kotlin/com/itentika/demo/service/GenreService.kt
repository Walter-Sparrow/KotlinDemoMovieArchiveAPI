package com.itentika.demo.service

import com.itentika.demo.dto.GenreDto
import com.itentika.demo.dto.PageDto
import com.itentika.demo.entity.Genre
import com.itentika.demo.exception.ResourceAlreadyExistsException
import com.itentika.demo.exception.ResourceNotFoundException
import com.itentika.demo.repository.GenreRepository
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
@AllArgsConstructor
class GenreService {
    private val genreRepository: GenreRepository? = null

    fun getById(id: Long): Genre {
        return genreRepository!!
            .findById(id)
            .orElseThrow {
                ResourceNotFoundException(
                    "The genre with id=$id does not exist."
                )
            }
    }

    fun getGenres(pageDto: PageDto): Page<Genre?> {
        return genreRepository!!.findAll(pageDto.pageable)
    }

    fun addGenre(genreDto: GenreDto): Genre {
        if (genreRepository!!.existsByName(genreDto.getName())) {
            throw ResourceAlreadyExistsException("The genre with such name already exists.")
        }

        val genre = genreDto.getName()?.let { Genre(it) }
        genreRepository.save(genre)
        return genre
    }

    fun updateGenre(id: Long, genreModel: GenreDto): Genre {
        val genre = genreRepository!!.findById(id)
        if (!genre.isPresent) {
            throw ResourceNotFoundException("The genre with id=$id does not exist.")
        }
        val genreFromDB = genre.get()
        genreFromDB.setName(genreModel.getName())
        genreRepository.save(genreFromDB)
        return genreFromDB
    }

    fun deleteById(id: Long) {
        genreRepository!!.deleteById(id)
    }
}