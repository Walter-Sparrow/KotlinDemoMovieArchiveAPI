package com.itentika.demo.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.Setter

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
class MovieCastDto {
    private val personId: Long? = null

    private val characterName: String? = null

    private val castOrder = 0
}