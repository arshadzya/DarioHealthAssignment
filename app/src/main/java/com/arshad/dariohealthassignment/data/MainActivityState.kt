package com.arshad.dariohealthassignment.data

/**
 * Data validation state of the login form.
 */
data class MainActivityState(
    val errorTypeError: Int? = null,
    val isDataValid: Boolean = false,
)