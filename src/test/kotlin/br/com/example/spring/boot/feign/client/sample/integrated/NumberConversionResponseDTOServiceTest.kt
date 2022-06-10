package br.com.example.spring.boot.feign.client.sample.integrated

import br.com.example.spring.boot.feign.client.sample.BaseIntegrationTest
import br.com.example.spring.boot.feign.client.sample.model.dto.request.NumberToDollarsRequestDTO
import br.com.example.spring.boot.feign.client.sample.model.dto.request.NumberToWordsRequestDTO
import br.com.example.spring.boot.feign.client.sample.service.NumberConversionService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.math.BigInteger

class NumberConversionResponseDTOServiceTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var numberConversionService: NumberConversionService

    @Test
    fun `GIVEN a number, WHEN call convertNumberToWords, THEN return number in full`() {
        val response = numberConversionService.convertNumberToWords(
            NumberToWordsRequestDTO(BigInteger.ONE)
        )
        assertEquals("one", response.result)
    }

    @Test
    fun `GIVEN a number too large, WHEN call convertNumberToWords, THEN return number too large message`() {
        val response = numberConversionService.convertNumberToWords(
            NumberToWordsRequestDTO(BigInteger(NUMBER_TOO_LARGE_VALUE))
        )
        assertEquals(NUMBER_TOO_LARGE_MESSAGE, response.result)
    }

    @Test
    fun `GIVEN a number, WHEN call convertNumberToDollars, THEN return dollar amount in full`() {
        val response = numberConversionService.convertNumberToDollars(
            NumberToDollarsRequestDTO(BigDecimal.ONE)
        )
        assertEquals("one dollar", response.result)
    }

    @Test
    fun `GIVEN a number too large, WHEN call convertNumberToDollars, THEN return number too large message`() {
        val response = numberConversionService.convertNumberToDollars(
            NumberToDollarsRequestDTO(BigDecimal(NUMBER_TOO_LARGE_VALUE))
        )
        assertEquals(NUMBER_TOO_LARGE_MESSAGE, response.result)
    }

    companion object {
        const val NUMBER_TOO_LARGE_MESSAGE = "number too large"
        const val NUMBER_TOO_LARGE_VALUE = "9999999999999999999"
    }
}