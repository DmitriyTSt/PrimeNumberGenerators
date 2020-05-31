package ru.dmitriyt.generators.generator

import java.math.BigInteger

class LFSR(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size != 4) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val p = i[0].toInt()
        val a = i[1].parseBigInteger()
        val y1p = i[2].parseBigInteger()
        val s = i[3].toInt()
        val x = ArrayList<Int>()
        val tempFirstX = toBin(y1p, p)
        val aArr = toBin(a, p)
        val j = aArr.mapIndexed { index, i -> index to i }.filter { it.second == 1 }.map { it.first }
        val m = j.size
        x.addAll(tempFirstX)
        repeat(n) { i ->
            repeat(s) { ss ->
                var newX = 0
                repeat(m) { k ->
                    newX += x[i * s + ss + j[k]]
                    newX %= 2
                }
                x.add(newX)
            }
            result.add(toDec(x.subList(x.lastIndex - s + 1, x.lastIndex + 1)))
            indicateGenerate(i + 1, n)
        }
        return result
    }

    private fun String.parseBigInteger(): BigInteger {
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