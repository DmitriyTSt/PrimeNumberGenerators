package ru.dmitriyt.generators.generator

import java.math.BigInteger

class RC4(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size != 257) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val k = i.subList(0, i.lastIndex).map { it.toInt() }
        val w = i.last().toInt()
        var j = 0
        val s = ArrayList<Int>()
        repeat(256) {
            s.add(it)
        }
        repeat(256) {
            j = (j + s[it] + k[it]) % 256
            val t = s[it]
            s[it] = s[j]
            s[j] = t
        }
        j = 0
        var i = 0
        var y = mutableListOf<Int>()
        var nn = n
        while (nn > 0) {
            i = (i + 1) % 256
            j = (j + s[i]) % 256
            val t = s[i]
            s[i] = s[j]
            s[j] = t
            val x = s[(s[i] + s[j]) % 256]
            val xBin = toBin(BigInteger(x.toString()))
            while (xBin.size < 8) {
                xBin.add(0, 0)
            }
            y.addAll(xBin)
            while (y.size >= w && nn > 0) {
                result.add(toDec(y.subList(0, w)))
                indicateGenerate(n - nn + 1, n)
                y = y.subList(w, y.size)
                nn--
            }
        }
        return result
    }
}