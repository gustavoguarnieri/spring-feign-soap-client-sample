package br.com.example.spring.boot.feign.client.sample.service

import br.com.example.spring.boot.feign.client.sample.client.NumberConversionClient
import br.com.example.spring.boot.feign.client.sample.model.dto.request.NumberToDollarsRequestDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.request.NumberToWordsRequestDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.response.NumberToDollarsResponseDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.response.NumberToWordsResponseDTO
import br.com.example.spring.boot.feign.client.sample.model.soap.NumberToDollars
import br.com.example.spring.boot.feign.client.sample.model.soap.NumberToWords
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class NumberConversionService(private val numberConversionClient: NumberConversionClient) {

    private val log = KotlinLogging.logger {}

    fun convertNumberToWords(
        numberToWordsRequestDTO: NumberToWordsRequestDTO
    ): NumberToWordsResponseDTO {
        return try {
            val numberToWords = NumberToWords().apply { ubiNum = numberToWordsRequestDTO.number }
            val numberToWordsResponse = numberConversionClient.convertNumberToWords(numberToWords)
            NumberToWordsResponseDTO().apply { result = numberToWordsResponse.numberToWordsResult.trim() }
        } catch (ex: Exception) {
            log.error {
                "convertNumberToWords: error converting number to words, " +
                        "message=${ex.message?.take(LOG_QUANTITY_OF_CHARACTERS)}"
            }
            throw ex
        }
    }

    fun convertNumberToDollars(
        numberToDollarsRequestDTO: NumberToDollarsRequestDTO
    ): NumberToDollarsResponseDTO {
        return try {
            val numberToDollars = NumberToDollars().apply { dNum = numberToDollarsRequestDTO.number }
            val numberToDollarsResponse = numberConversionClient.convertNumberToDollars(numberToDollars)
            NumberToDollarsResponseDTO().apply { result = numberToDollarsResponse.numberToDollarsResult }
        } catch (ex: Exception) {
            log.error {
                "convertNumberToDollars: error converting number to dollars, " +
                        "message=${ex.message?.take(LOG_QUANTITY_OF_CHARACTERS)}"
            }
            throw ex
        }
    }

    companion object {
        const val LOG_QUANTITY_OF_CHARACTERS = 150
    }
}