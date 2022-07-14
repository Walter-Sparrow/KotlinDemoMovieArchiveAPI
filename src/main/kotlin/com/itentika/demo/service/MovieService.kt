package com.itentika.demo.service

import com.itentika.demo.dto.MovieCastDto
import com.itentika.demo.dto.MovieDto
import com.itentika.demo.dto.PageDto
import com.itentika.demo.entity.Genre
import com.itentika.demo.entity.Movie
import com.itentika.demo.entity.MovieCast
import com.itentika.demo.exception.ResourceAlreadyExistsException
import com.itentika.demo.exception.ResourceNotFoundException
import com.itentika.demo.repository.GenreRepository
import com.itentika.demo.repository.MovieCastRepository
import com.itentika.demo.repository.MovieRepository
import com.itentika.demo.repository.PersonRepository
import lombok.AllArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
@AllArgsConstructor
class MovieService {
    private val movieRepository: MovieRepository? = null
    private val genreRepository: GenreRepository? = null
    private val personRepository: PersonRepository? = null
    private val movieCastRepository: MovieCastRepository? = null

    fun getById(id: Long): Movie {
        return movieRepository!!
            .findById(id)
            .orElseThrow { ResourceNotFoundException("The movie with id=$id does not exist.") }
    }

    fun getMovies(pageDto: PageDto): Page<Movie> {
        return movieRepository!!.findAll(pageDto.pageable)
    }

    fun addMovie(movieDto: MovieDto): Movie {
        if (movieRepository!!.existsByTitleAndReleaseDate(movieDto.getTitle(), movieDto.getReleaseDate())) {
            throw ResourceAlreadyExistsException("The movie with such title and release date already exists.")
        }

        validateGenres(movieDto)
        validateActors(movieDto)
        val movie = Movie.builder()
            .title(movieDto.getTitle())
            .plot(movieDto.getPlot())
            .budget(movieDto.getBudget())
            .releaseDate(movieDto.getReleaseDate())
            .revenue(movieDto.getRevenue())
            .runtime(movieDto.getRuntime())
            .tagline(movieDto.getTagline())
            .genres(getGenreSet(movieDto))
            .build()

        movieRepository.save(movie)

        val movieCasts = getMovieCastSet(movieDto, movie)
        movie.setActors(movieCasts)
        movieCasts.stream().filter { obj: MovieCast? -> Objects.nonNull(obj) }.forEach { entity: MovieCast -> movieCastRepository!!.save(entity) }
        movieRepository.save(movie)

        return movie
    }

    private fun getMovieCastSet(movieDto: MovieDto, movie: Movie): Set<MovieCast> {
        return movieDto.getActors().stream()
            .map { castDto: MovieCastDto ->
                val movieCast = MovieCast()

                movieCast.setMovie(movie)
                movieCast.setPerson(personRepository!!.getById(castDto.getPersonId()))
                movieCast.setCharacterName(castDto.getCharacterName())
                movieCast.setCastOrder(castDto.getCastOrder())
                movieCast
            }
            .collect(Collectors.toSet())
    }

    private fun getGenreSet(movieDto: MovieDto): Set<Genre> {
        return movieDto.getGenres().stream()
            .map { id: Long -> genreRepository!!.findById(id) }
            .filter { obj: Optional<Genre?> -> obj.isPresent }
            .map { obj: Optional<Genre?> -> obj.get() }
            .collect(Collectors.toSet())
    }

    private fun validateGenres(movieDto: MovieDto) {
        movieDto.getGenres().stream()
            .filter { genreId: Long -> !genreRepository!!.existsById(genreId) }
            .findFirst()
            .ifPresent { throw ResourceNotFoundException("Certain genres don't exist.") }
    }

    private fun validateActors(movieDto: MovieDto) {
        movieDto.getActors().stream()
            .filter { actor: MovieCastDto -> !personRepository!!.existsById(actor.getPersonId()) }
            .findFirst()
            .ifPresent { throw ResourceNotFoundException("Certain persons don't exist.") }
    }

    fun updateMovie(id: Long, movieDto: MovieDto): Movie {
        val movie = movieRepository!!.findById(id)
        if (!movie.isPresent) {
            throw ResourceNotFoundException("The movie with id=$id does not exist.")
        }
        validateGenres(movieDto)
        val movieFromDB = movie.get()
        movieFromDB.setTitle(movieDto.getTitle())
        movieFromDB.setReleaseDate(movieDto.getReleaseDate())
        movieFromDB.setBudget(movieDto.getBudget())
        movieFromDB.setPlot(movieDto.getPlot())
        movieFromDB.setRevenue(movieDto.getRevenue())
        movieFromDB.setRuntime(movieDto.getRuntime())
        movieFromDB.setTagline(movieDto.getTagline())
        movieFromDB.setGenres(getGenreSet(movieDto))
        movieFromDB.setActors(getMovieCastSet(movieDto, movieFromDB))
        movieFromDB.setActors(
            movieFromDB.getActors()
                .stream()
                .filter { obj: MovieCast? -> Objects.nonNull(obj) }
                .forEach { entity: MovieCast -> movieCastRepository!!.save(entity) })
        movieRepository.save(movieFromDB)
        return movieFromDB
    }

    fun deleteById(id: Long) {
        movieRepository!!.findById(id).ifPresent { movie: Movie -> movieCastRepository!!.deleteAllByMovie(movie) }
        movieRepository.deleteById(id)
    }
}