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

    private fun <T : Number> pushNumber(number: T) {
        if (outputFile != null) {
            outputFile?.appendText("$number ")
        } else {
            println(number)
        }
    }

    protected fun indicateGenerate(current: Int, count: Int) {
        System.err.println("$current/$count")
    }

    protected fun toBin(value: BigInteger, size: Int = 0): MutableList<Int> {
        val ans = value.toString(2).map { it - '0' }.toMutableList()
        while (ans.size < size) {
            ans.add(0, 0)
        }
        return ans
    }

    protected fun toDec(x: MutableList<Int>): BigInteger {
        return x.joinToString("").toBigInteger(2)
    }


    protected fun String.parseBigInteger(): BigInteger {
        val xPos = this.indexOf('x')
        val bPos = this.indexOf('b')
        return if (xPos == -1 && bPos == -1) {
            this.toBigInteger()
        } else if (xPos != -1) {
            this.substring(2).toBigInteger(16)
        } else {
            this.substring(2).toBigInteger(2)
        }
    }
}