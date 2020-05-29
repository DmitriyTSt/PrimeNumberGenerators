package ru.dmitriyt.generators.generator

import java.math.BigInteger

class ADD(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size < 4 || i.size % 2 == 1) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val s = (i.size - 2) / 2
        val a = i.subList(0, s).map { it.toBigInteger() }
        val c = i[s].toBigInteger()
        val mm = i[s + 1].toBigInteger()
        val x = i.subList(s + 2, i.size).map { it.toBigInteger() }.toMutableList()
        repeat(n) { i ->
            var tempX = c % mm
            repeat(s) {
                tempX = (tempX + a[it] * x[i + it]) % mm
            }
            x.add(tempX)
            result.add(tempX)
            indicateGenerate(i + 1, n)
        }
        return result
    }
}