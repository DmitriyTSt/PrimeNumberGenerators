package ru.dmitriyt.generators.generator

import java.math.BigInteger

class BBS(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size != 3) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val nAlg = i[0].toBigInteger()
        val l = i[1].toInt()
        var x = i[2].toBigInteger()
        repeat(n) {
            val s = arrayListOf<Int>()
            repeat(l) {
                x = x.modPow(2.toBigInteger(), nAlg)
                val a = toBin(x, l)
                s += a.last()
            }
            result.add(toDec(s))
            indicateGenerate(it + 1, n)
        }
        return result
    }
}