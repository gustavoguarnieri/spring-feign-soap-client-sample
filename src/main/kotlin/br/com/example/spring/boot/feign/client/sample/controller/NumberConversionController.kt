package br.com.example.spring.boot.feign.client.sample.controller

import br.com.example.spring.boot.feign.client.sample.model.dto.request.NumberToDollarsRequestDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.request.NumberToWordsRequestDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.response.NumberToDollarsResponseDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.response.NumberToWordsResponseDTO
import br.com.example.spring.boot.feign.client.sample.service.NumberConversionService
import mu.KotlinLogging
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/conversions")
@Validated
class NumberConversionController(private val numberConversionService: NumberConversionService) {

    private val log = KotlinLogging.logger {}

    @PostMapping("/words")
    fun convertNumberToWords(
        @Valid @RequestBody numberToWordsRequestDTO: NumberToWordsRequestDTO
    ): NumberToWordsResponseDTO {
        log.info {
            "convertNumberToWords: starting to convert number to words, number=${numberToWordsRequestDTO.number}"
        }
        return numberConversionService.convertNumberToWords(numberToWordsRequestDTO).also {
            log.info {
                "convertNumberToWords: finished to convert number to words, number=${numberToWordsRequestDTO.number}"
            }
        }
    }

    @PostMapping("/dollars")
    fun convertNumberToDollars(
        @Valid @RequestBody numberToDollarsRequestDTO: NumberToDollarsRequestDTO
    ): NumberToDollarsResponseDTO {
        log.info {
            "convertNumberToDollars: starting to convert number to dollars, number=${numberToDollarsRequestDTO.number}"
        }
        return numberConversionService.convertNumberToDollars(numberToDollarsRequestDTO).also {
            log.info {
                "convertNumberToDollars: finished to convert number to dollars, " +
                        "number=${numberToDollarsRequestDTO.number}"
            }
        }
    }
}