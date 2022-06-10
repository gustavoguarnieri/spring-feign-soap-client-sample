package br.com.example.spring.boot.feign.client.sample.client

import br.com.example.spring.boot.feign.client.sample.config.WebServiceConfig
import br.com.example.spring.boot.feign.client.sample.model.soap.NumberToDollars
import br.com.example.spring.boot.feign.client.sample.model.soap.NumberToDollarsResponse
import br.com.example.spring.boot.feign.client.sample.model.soap.NumberToWords
import br.com.example.spring.boot.feign.client.sample.model.soap.NumberToWordsResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "NUMBER-CONVERSION",
    url = "\${host.soap.number-conversion.url}",
    configuration = [WebServiceConfig::class]
)
interface NumberConversionClient {
    @PostMapping(produces = [MediaType.TEXT_XML_VALUE], consumes = [MediaType.TEXT_XML_VALUE])
    fun convertNumberToWords(@RequestBody request: NumberToWords): NumberToWordsResponse

    @PostMapping(produces = [MediaType.TEXT_XML_VALUE], consumes = [MediaType.TEXT_XML_VALUE])
    fun convertNumberToDollars(@RequestBody request: NumberToDollars): NumberToDollarsResponse
}