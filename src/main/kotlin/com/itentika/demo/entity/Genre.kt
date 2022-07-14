package com.itentika.demo.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToMany

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private val id: Long? = null

    @Column(name = "genre_name")
    private val name: String = ""

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private val movies: Set<Movie>? = null
}