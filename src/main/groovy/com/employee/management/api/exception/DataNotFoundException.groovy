package com.employee.management.api.exception

class DataNotFoundException extends RuntimeException {

    DataNotFoundException(String message) {
        super("No data found")
    }
}
