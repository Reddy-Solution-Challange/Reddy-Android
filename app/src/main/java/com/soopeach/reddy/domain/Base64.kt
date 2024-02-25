package com.soopeach.reddy.domain

import java.util.Base64

object Base64Utils {

    private val encoder = Base64.getEncoder()
    private val decoder = Base64.getDecoder()

    fun encode(input: String) = encoder.encodeToString(input.toByteArray())

    fun decode(input: String) = String(decoder.decode(input))

}