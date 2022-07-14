package com.itentika.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.sun.istack.NotNull
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter
import javax.persistence.*

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "movie_cast")
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
class MovieCast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_cast_id")
    private val id: Long? = null

    @JsonIgnore
    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "MOVIE_ID")
    private val movie: Movie? = null

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "PERSON_ID")
    private val person: Person? = null

    @NotNull
    @Column(name = "character_name")
    private val characterName: String? = null

    @Column(name = "cast_order")
    private val castOrder = 0
}