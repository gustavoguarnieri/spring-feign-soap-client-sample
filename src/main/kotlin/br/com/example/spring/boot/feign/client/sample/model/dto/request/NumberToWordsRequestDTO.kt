package br.com.example.spring.boot.feign.client.sample.model.dto.request

import java.math.BigInteger
import javax.validation.constraints.Positive

class NumberToWordsRequestDTO(
    @field:Positive(message = "the number must be positive")
    val number: BigInteger? = null
)
