package com.itentika.demo.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter
import java.sql.Date

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
class MovieDto {
    private val title: String? = null

    private val releaseDate: Date? = null

    private val budget = 0

    private val plot: String? = null

    private val revenue = 0

    private val runtime = 0

    private val tagline: String? = null

    private val genres: Set<Long>? = null

    private val actors: Set<MovieCastDto>? = null
}