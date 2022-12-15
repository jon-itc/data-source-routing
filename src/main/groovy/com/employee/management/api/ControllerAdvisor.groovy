package com.employee.management.api

import com.employee.management.api.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import java.time.LocalDateTime

@ControllerAdvice
class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    ResponseEntity<Object> handleDataNotFoundException() {

        Map<String, Object> body = new LinkedHashMap<>()
        body.put("timestamp", LocalDateTime.now())
        body.put("message", "City not found")

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND)
    }

}
