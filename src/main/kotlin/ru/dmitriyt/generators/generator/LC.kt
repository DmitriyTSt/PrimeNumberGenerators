package ru.dmitriyt.generators.generator

import java.math.BigInteger

class LC(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i?.size != 4) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val a = i[0].toBigInteger()
        val c = i[1].toBigInteger()
        val mm = i[2].toBigInteger()
        val x0 = i[3].toBigInteger()
        var x = x0
        repeat(n) {
            x = (a * x + c) % mm
            result.add(x)
            indicateGenerate(it + 1, n)
        }
        return result
    }
}