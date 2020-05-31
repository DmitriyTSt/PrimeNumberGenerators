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