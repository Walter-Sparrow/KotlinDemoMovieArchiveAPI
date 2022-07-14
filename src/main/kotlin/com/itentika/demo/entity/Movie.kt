package com.itentika.demo.entity

import com.sun.istack.NotNull
import lombok.*
import java.sql.Date
import javax.persistence.*


@Entity
@Setter
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private val id: Long? = null

    @NotNull
    @Column(name = "title")
    private val title: String? = null

    @NotNull
    @Column(name = "release_date")
    private val releaseDate: Date? = null

    @Column(name = "budget")
    private val budget = 0

    @NotNull
    @Column(name = "plot")
    private val plot: String? = null

    @Column(name = "revenue")
    private val revenue = 0

    @Column(name = "runtime")
    private val runtime = 0

    @NotNull
    @Column(name = "tagline")
    private val tagline: String? = null

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = [JoinColumn(name = "movie_id")], inverseJoinColumns = [JoinColumn(name = "genre_id")])
    private val genres: Set<Genre>? = null

    @OneToMany(mappedBy = "movie")
    private val actors: Set<MovieCast>? = null
}