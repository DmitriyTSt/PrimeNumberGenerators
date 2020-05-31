package ru.dmitriyt.generators.generator

import java.math.BigInteger

class MT(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size < 11) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val p = i[0].toInt()
        val w = i[1].toInt()
        val r = i[2].toInt()
        val q = i[3].toInt()
        val a = i[4].toBigInteger()
        val u = i[5].toInt()
        val s = i[6].toInt()
        val t = i[7].toInt()
        val l = i[8].toInt()
        val b = i[9].toBigInteger()
        val c = i[10].toBigInteger()
        val x = i.subList(11, i.size).map { it.toBigInteger() }.toMutableList()
        if (x.size != p) {
            throw Exception("Incorrect param i")
        }

        repeat(n) {
            val temp = merge(x[it], x[it + 1], r, w)
            x.add(x[it + q].xor(temp.shr(1)).xor(a * (temp % BigInteger("2"))).rem(BigInteger("2").pow(w)))
            result.add(zak(x[it + p], u, s, t, l, b, c, w))
            indicateGenerate(it + 1, n)
        }
        return result
    }

    private fun zak(x: BigInteger, u: Int, s: Int, t: Int, l: Int, b: BigInteger, c: BigInteger, w: Int): BigInteger {
        var y = x.xor(x.shr(u)).rem(BigInteger("2").pow(w))
        y = y.xor(y.shl(s) and b)
        y = y.xor(y.shl(t) and c)
        return y.xor(y.shr(l)).rem(BigInteger("2").pow(w))
    }

    private fun merge(a: BigInteger, b: BigInteger, r: Int, w: Int): BigInteger {
        val aBin = toBin(a)
        val bBin = toBin(b)
        while (aBin.size < w) {
            aBin.add(0, 0)
        }
        while (bBin.size < w) {
            bBin.add(0, 0)
        }
        return toDec((aBin.subList(0, aBin.size - r) + bBin.subList(bBin.size - r, bBin.size)).toMutableList())
    }
}