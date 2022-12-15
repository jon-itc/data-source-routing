package com.employee.management.config.datasource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.stereotype.Component

import javax.sql.DataSource

@Component
class DataSourceRouting extends AbstractRoutingDataSource {

    private final MasterDataSourceProperties masterDataSourceProperties
    private final SlaveDataSourceProperties slaveDataSourceProperties

    @Autowired
    DataSourceRouting(MasterDataSourceProperties masterDataSourceProperties, SlaveDataSourceProperties slaveDataSourceProperties) {
        this.masterDataSourceProperties = masterDataSourceProperties
        this.slaveDataSourceProperties = slaveDataSourceProperties
        initDataSourceRoutingMap()
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getEnvironment()
    }

    DataSource slaveDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource()
        dataSource.setUrl(slaveDataSourceProperties.url)
        dataSource.setUsername(slaveDataSourceProperties.username)
        dataSource.setPassword(slaveDataSourceProperties.password)
        return dataSource
    }

    DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource()
        dataSource.setUrl(masterDataSourceProperties.url)
        dataSource.setUsername(masterDataSourceProperties.username)
        dataSource.setPassword(masterDataSourceProperties.password)
        return dataSource
    }

    private initDataSourceRoutingMap() {
        Map<Object, Object> dataSourceMap = new HashMap<>()
        dataSourceMap.put(DatabaseEnvironment.READONLY, slaveDataSource())
        dataSourceMap.put(DatabaseEnvironment.UPDATABLE, masterDataSource())
        this.setTargetDataSources(dataSourceMap)
        this.setDefaultTargetDataSource(masterDataSource())
    }
}
