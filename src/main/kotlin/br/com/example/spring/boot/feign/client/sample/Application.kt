package br.com.example.spring.boot.feign.client.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(basePackages = ["br.com.example.spring.boot.feign.client.sample.client"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
