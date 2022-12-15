package com.employee.management.config.datasource

class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseEnvironment> CONTEXT = new ThreadLocal<>()

    static void set(DatabaseEnvironment databaseEnvironment) {
        CONTEXT.set(databaseEnvironment)
    }

    static DatabaseEnvironment getEnvironment() {
        return CONTEXT.get()
    }

    static void reset() {
        CONTEXT.set(DatabaseEnvironment.UPDATABLE)
    }
}
