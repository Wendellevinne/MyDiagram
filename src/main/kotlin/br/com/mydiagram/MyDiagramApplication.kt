package br.com.mydiagram

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyDiagramApplication

fun main(args: Array<String>) {
	runApplication<MyDiagramApplication>(*args)
}
