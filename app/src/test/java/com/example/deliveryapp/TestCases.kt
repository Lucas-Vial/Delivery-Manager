package com.example.deliveryapp

import Testing
import junit.framework.TestCase.assertTrue
import org.junit.Test

class TestCases {

    @Test
    fun test(){
        val origin = "33 Jackie Howe Crescent, Macarthur, ACT, Australia"
        val dest = "2/38-70 Gartside St, Wanniassa, ACT, Australia"

        val e = Testing.calcTime(origin, dest)
        print(e)
        assertTrue(e > 1)
    }
}