package br.com.example.spring.boot.feign.client.sample.config

import feign.codec.Decoder
import feign.codec.Encoder
import feign.jaxb.JAXBContextFactory
import feign.soap.SOAPDecoder
import feign.soap.SOAPEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebServiceConfig {
    private val jaxbFactory = JAXBContextFactory.Builder()
        .withMarshallerSchemaLocation("\${host.soap.number-conversion.url} \${host.soap.number-conversion.wsdl}")
        .withMarshallerJAXBEncoding("UTF-8")
        .build()

    @Bean
    fun soapEncoder(): Encoder {
        return SOAPEncoder(jaxbFactory)
    }

    @Bean
    fun soapDecoder(): Decoder {
        return SOAPDecoder(jaxbFactory)
    }
}