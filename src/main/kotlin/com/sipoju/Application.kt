package com.sipoju

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.sipoju")
                .mainClass(Application.javaClass)
                .start()
    }
}