package com.example.suitmedia_km_test.helpers

object Utils {
    fun palindromeChecker(word : String): Boolean{
        val currentWord = StringBuilder(word)
        val reverseWord = currentWord.reverse().toString()
        return word == reverseWord
    }
}