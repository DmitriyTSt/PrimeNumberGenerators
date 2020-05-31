package ru.dmitriyt.generators.generator

import java.math.BigInteger

class RSA(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size != 5) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val nAlg = i[0].toBigInteger()
        val e = i[1].toBigInteger()
        val w = i[2].toInt()
        val l = i[3].toInt()
        var x = i[4].toBigInteger()
        val s = arrayListOf<Int>()
        var nn = n
        while (nn > 0) {
            x = x.modPow(e, nAlg)
            val a = toBin(x, w)
            s += a.subList(a.lastIndex + 1 - w, a.lastIndex + 1)
            while (s.size >= l) {
                result.add(toDec(s.subList(0, l)))
                indicateGenerate(n - nn + 1, n)
                repeat(l) {
                    s.removeAt(0)
                }
                nn--
            }
        }
        return result
    }
}