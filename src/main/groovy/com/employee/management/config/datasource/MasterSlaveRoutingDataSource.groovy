package com.employee.management.config.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getEnvironment()
    }
}
