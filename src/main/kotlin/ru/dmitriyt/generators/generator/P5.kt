package ru.dmitriyt.generators.generator

import java.math.BigInteger

class P5(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size != 6) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val p = i[0].toInt()
        val q1 = i[1].toInt()
        val q2 = i[2].toInt()
        val q3 = i[3].toInt()
        val w = i[4].toInt()
        var y1p = i[5].toBigInteger()
        val x = ArrayList<Int>()
        val tempFirstX = ArrayList<Int>()
        while (y1p > BigInteger("1")) {
            tempFirstX.add((y1p % BigInteger("2")).toInt())
            y1p /= BigInteger("2")
        }
        tempFirstX.add((y1p % BigInteger("2")).toInt())
        while (tempFirstX.size < p) {
            tempFirstX.add(0)
        }
        x.addAll(tempFirstX.reversed())
        repeat(n) { i ->
            repeat(w) { ss ->
                x.add((x[i * w + ss + q1]+ x[i * w + ss + q2]+ x[i * w + ss + q3] + x[i * w + ss]) % 2)
            }
            result.add(
                x.subList(x.lastIndex - w + 1, x.lastIndex + 1).joinToString("").toBigInteger(2)
            )
            indicateGenerate(i + 1, n)
        }
        return result
    }
}