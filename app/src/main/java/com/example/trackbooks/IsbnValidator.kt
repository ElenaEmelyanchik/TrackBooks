package com.example.trackbooks

object IsbnValidator {

    fun validateIsbn13(isbnNumber: String): Boolean {
        val isbn = replaceHyphens(isbnNumber)
        var totalSum = 0
        try {
            for (i in 0..11) {
                val digit = Integer.parseInt(isbn.substring(i, i + 1))
                totalSum += if (i % 2 == 0) digit * 1 else digit * 3
            }
            var checksum = 10 - totalSum % 10
            if (checksum == 10) {
                checksum = 0
            }

            return checksum == Integer.parseInt(isbn.substring(12))
        } catch (exception: NumberFormatException) {
            return false
        }
    }

    fun validateIsbn10(isbnNumber: String): Boolean {
        val isbn = replaceHyphens(isbnNumber)

        var totalSum = 0
        try {
            for (i in 0..8) {
                val digit = Integer.parseInt(isbn.substring(i, i + 1))
                totalSum += (10 - i) * digit
            }
            var checksum = Integer.toString((11 - totalSum % 11) % 11)
            if ("10" == checksum) {
                checksum = "X"
            }
            return checksum == isbn.substring(9)
        } catch (exception: NumberFormatException) {
            return false
        }
    }

    private fun replaceHyphens(isbnNumber: String) = isbnNumber.replace("-".toRegex(), "")
}