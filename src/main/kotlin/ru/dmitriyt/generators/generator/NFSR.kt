package ru.dmitriyt.generators.generator

import java.math.BigInteger

class NFSR(
    filePath: String?,
    private val n: Int,
    m: BigInteger?,
    private val i: List<String>?
) : BaseGenerator(filePath, m) {
    override fun generateNumbers(): List<Number> {
        if (i == null || i.size < 3) {
            throw Exception("Incorrect param i")
        }
        val result = mutableListOf<BigInteger>()
        val k = i[0].toInt()
        val w = i[1].toInt()
        if (i.size < k + 3) {
            throw Exception("Incorrect param i")
        }
        val rslosInit = i.subList(2, 2 + k * 3)
        val jj = i.subList(2 + k * 3, i.size).map { toBin(it.parseBigInteger(), k).reversed() }
        val p = rslosInit.filterIndexed { index, s -> index % 3 == 0 }.map { it.toInt() }
        val a = rslosInit.filterIndexed { index, s -> index % 3 == 1 }.map { it.parseBigInteger() }
        val start = rslosInit.filterIndexed { index, s -> index % 3 == 2 }.map { it.parseBigInteger() }
        val q = jj.size
        val rslosStreams = ArrayList<ArrayList<Int>>()
        repeat(k) {
            rslosStreams.add(rslos(n * w / p[it] + 1, p[it], a[it], start[it]))
        }
        repeat(n) { cc ->
            val resultStream = ArrayList<Int>()
            repeat(w) { ww ->
                var newX = 0
                val bits = rslosStreams.map { it[w * cc + ww] }
                repeat(q) {
                    val needBits = bits.mapIndexed { index, bit -> if (jj[it][index] == 0) 1 else bit }
                    var qq = needBits[0]
                    needBits.subList(1, bits.size).forEach {
                        qq = qq and it
                    }
                    newX = newX.xor(qq)
                }
                resultStream.add(newX)
            }
            println(toDec(resultStream))
        }
        return result
    }

    private fun rslos(n: Int, p: Int, a: BigInteger, start: BigInteger): ArrayList<Int> {
        val aArr = toBin(a, p).reversed()
        val j = aArr.mapIndexed { index, i -> index to i }.filter { it.second == 1 }.map { it.first }
        val m = j.size
        val x = ArrayList<Int>()
        val tempFirstX = toBin(start, p)
        x.addAll(tempFirstX)
        repeat(n) { i ->
            repeat(p) { ss ->
                var newX = 0
                repeat(m) { k ->
                    newX += x[i * p + ss + j[k]]
                    newX %= 2
                }
                x.add(newX)
            }
        }
        return x.subList(tempFirstX.size, x.size).toMutableList() as ArrayList<Int>
    }
}