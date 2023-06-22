package br.com.mydiagram

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class MyDiagramApplication

fun main(args: Array<String>) {
	runApplication<MyDiagramApplication>(*args)
}
