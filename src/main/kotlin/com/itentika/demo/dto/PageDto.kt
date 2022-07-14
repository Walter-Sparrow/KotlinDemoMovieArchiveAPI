package com.itentika.demo.dto

import lombok.Data
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@Data
class PageDto {
    private val pageNumber = 0
    private val pageSize = 5
    private val sortDirection = Sort.Direction.ASC
    private val sortBy = "id"
    val pageable: Pageable
        get() = PageRequest.of(
            pageNumber,
            pageSize,
            sortDirection,
            sortBy
        )
}