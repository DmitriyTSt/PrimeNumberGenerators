package ru.dmitriyt.generators.generator

import java.io.File
import java.io.IOException
import java.math.BigInteger

abstract class BaseGenerator(filePath: String?, private val m: BigInteger?) {
    private var outputFile: File? = null

    init {
        try {
            filePath?.let {
                outputFile = File(it)
                outputFile?.writeText("")
            }
        } catch (e: IOException) {
            println("Ошибка доступа к файлу")
        }
    }

    protected abstract fun generateNumbers(): List<Number>

    fun generate() {
        val numbers = generateNumbers()
        numbers.forEach {
            val number = if (m != null) {
                it.toString().toBigInteger() % m
            } else {
                it
            }
            pushNumber(number)
        }
        println("Успешная генерация")
    }

    protected fun <T : Number> pushNumber(number: T) {
        if (outputFile != null) {
            outputFile?.appendText("$number ")
        } else {
            println(number)
        }
    }
}