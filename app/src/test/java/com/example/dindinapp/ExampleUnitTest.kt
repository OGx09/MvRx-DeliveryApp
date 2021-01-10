package com.example.dindinapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        System.out.println("=====" + MockWebServer().mock().toString())
        assertEquals(4, 2 + 2)
    }
}