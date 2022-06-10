package br.com.example.spring.boot.feign.client.sample.model.dto.request

import java.math.BigDecimal
import javax.validation.constraints.Positive

class NumberToDollarsRequestDTO(
    @field:Positive(message = "the number must be positive")
    val number: BigDecimal? = null
)
