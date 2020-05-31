package ru.dmitriyt.generators

import ru.dmitriyt.generators.generator.*

class Router(private val args: Array<String>) {
    companion object {
        private const val DEFAULT_COUNT = 10000
    }

    fun start() {
        val arguments = parseArguments()
        if (arguments.containsKey(ArgsHelper.HELP.code)) {
            println("Описание параметров")
            println("/<code>:<value>")
            ArgsHelper.values().forEach {
                println("${it.code} ${it.description}")
            }
            println("Примеры запуска генераторов (иницилизационного вектора) (примеры со Stepik):")
            println("/g:lc /n:10 /i:3,10,64,1")
            println("/g:add /n:10 /i:1,3,5,7,93,2,4,6")
            println("/g:5p /n:5 /i:8,1,3,7,5,12")
            println("/g:lfsr /n:5 /i:5,0b10001,12,5")
            println("/g:nfsr /n:5 /i:3,107,24,0b100000000000001000000001,2312357,7,0b1000011,33,10,0b101000101,7,0b011,0b100,0b111,0b000")
            println("/g:mt /n:10 /i:5,4,2,3,13,2,1,1,3,4,6,4,7,5,2,9")
            println("/g:rc4 /n:10 /i:86,114,217,41,207,80,50,231,180,228,211,103,48,90,105,120,237,240,253,118,187,230," +
                    "21,144,117,26,252,226,87,191,19,65,71,236,147,248,36,84,245,178,208,210,64,176,42,66,166,51,7,12," +
                    "222,206,162,229,18,129,9,13,34,213,101,216,20,89,112,56,215,171,95,239,72,94,30,106,141,63,33,235," +
                    "138,93,212,85,104,61,238,44,10,193,70,124,116,81,68,243,99,155,74,24,196,160,125,158,165,122,205," +
                    "29,223,140,186,254,249,188,137,123,174,173,185,214,246,115,46,14,17,11,60,37,164,3,97,6,35,108," +
                    "119,43,153,146,255,200,136,31,58,5,27,151,57,242,22,190,109,47,224,143,184,232,227,149,163,96,110," +
                    "142,82,111,49,203,69,98,126,38,113,28,15,195,55,67,40,159,88,152,100,194,102,52,250,218,1,167,169," +
                    "0,83,189,179,247,177,181,168,75,198,92,91,134,148,161,241,32,183,130,77,175,78,2,133,127,39,244," +
                    "221,54,234,8,139,201,251,62,4,192,16,107,157,59,121,204,156,25,145,73,202,172,154,131,128,220,79," +
                    "209,219,197,132,225,45,23,135,233,53,199,76,150,170,182,9")
            println("/g:rsa /n:5 /i:221,3,5,4,23")
            println("/g:bbs /n:5 /i:437,10,97")
        } else {
            val generator = when (arguments[ArgsHelper.GENERATOR.code]) {
                "lc" -> {
                    LC(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "add" -> {
                    ADD(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "5p" -> {
                    P5(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "mt" -> {
                    MT(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "rc4" -> {
                    RC4(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "rsa" -> {
                    RSA(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "bbs" -> {
                    BBS(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "lfsr" -> {
                    LFSR(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                "nfsr" -> {
                    NFSR(
                        arguments[ArgsHelper.FILE.code],
                        arguments[ArgsHelper.COUNT.code]?.toInt() ?: DEFAULT_COUNT,
                        arguments[ArgsHelper.MOD.code]?.toBigInteger(),
                        arguments[ArgsHelper.I_VECTOR.code]?.split(",")
                    )
                }
                else -> {
                    println("Неизвестный код генератора")
                    null
                }
            }
            generator?.generate()
        }
    }

    private fun parseArguments(): HashMap<String, String> {
        val arguments = hashMapOf<String, String>()
        args.forEach {
            val divider = it.indexOf(":")
            if (divider != -1) {
                arguments[it.substring(1, divider)] = it.substring(divider + 1)
            } else {
                arguments[it.substring(1)] = ""
            }

        }
        return arguments
    }
}