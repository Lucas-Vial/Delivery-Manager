package com.example.deliveryapp

import Testing
import junit.framework.TestCase.assertTrue
import org.junit.Test

class TestCases {
    val origin = "2/38-70 Gartside St, Wanniassa, ACT, Australia"
    val dest = "33 Jackie Howe Crescent, Macarthur, ACT, Australia"
    val dest2 = "65 Norriss St, Chisholm, ACT, Australia"
    val dest3 = "3 Prichard Cct, Richardson, ACT, Australia"

    @Test
    fun testTesting(){
        val e = Testing.calcTime(origin, dest)
        print(e)
        assertTrue(e > 1)
    }

    @Test
    fun bestRoute(){
        val a = arrayOf(dest, dest2, dest3)
        print(BestRoute.computeRoutes(a))
    }
}