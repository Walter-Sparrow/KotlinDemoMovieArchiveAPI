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
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
class Person(@field:Column(name = "person_name") @field:NotNull private val name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private val id: Long? = null

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private val movies: Set<MovieCast>? = null
}