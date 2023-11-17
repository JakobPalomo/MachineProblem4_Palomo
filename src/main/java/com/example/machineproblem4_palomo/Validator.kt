package com.example.machineproblem4_palomo

class Validator {
    public fun isStrongPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{5,}\$".toRegex()
        return passwordPattern.matches(password)
    }
}