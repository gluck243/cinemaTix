package com.gluck.cinemaTix.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.CONFLICT)
class InvalidBookingException(message: String): RuntimeException(message) {
}