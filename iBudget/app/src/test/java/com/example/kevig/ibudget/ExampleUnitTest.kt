package com.example.kevig.ibudget

import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.LocalDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun displayDateTime() {
        val dt = LocalDateTime.now().toString()
        println(dt.substring(0,dt.indexOf("T")))
    }
}
