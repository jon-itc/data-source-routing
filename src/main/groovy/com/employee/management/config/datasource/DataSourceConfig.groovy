package com.employee.management.config.datasource


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.sql.DataSource

@EnableTransactionManagement
@Configuration
@DependsOn("dataSourceRouting")
class DataSourceConfig {

    @Bean
    @Primary
    DataSource dataSource(DataSourceRouting dataSourceRouting) {
        return dataSourceRouting
    }

    /* @Bean
     DataSource dataSource() {
         MasterSlaveRoutingDataSource masterSlaveRoutingDataSource = new MasterSlaveRoutingDataSource()
         Map<Object, Object> targetDataSources = new HashMap<>()
         targetDataSources.put(DatabaseEnvironment.UPDATABLE, masterDataSource())
         targetDataSources.put(DatabaseEnvironment.READONLY, slaveDataSource())
         masterSlaveRoutingDataSource.setTargetDataSources(targetDataSources)

         // Set as all transaction point to master
         masterSlaveRoutingDataSource.setDefaultTargetDataSource(masterDataSource())
         return masterSlaveRoutingDataSource
     }

     DataSource slaveDataSource() {
         return slaveDataSourceProperties()
                 .initializeDataSourceBuilder()
                 .build()
     }

     DataSource masterDataSource() {
         return masterDataSourceProperties()
                 .initializeDataSourceBuilder()
                 .build()
     }

     *//*DataSource slaveDataSource() {
         DataSourceProperties props = slaveDataSourceProperties()
         HikariDataSource hikariDataSource = new HikariDataSource()
         hikariDataSource.setJdbcUrl(props.url)
         hikariDataSource.setUsername(props.username)
         hikariDataSource.setPassword(props.password)
         return hikariDataSource
 *//*/*
         return slaveDataSourceProperties()
                 .initializeDataSourceBuilder()
                 .build()*//*/*
     }

     DataSource masterDataSource() {
         DataSourceProperties props = masterDataSourceProperties()
         HikariDataSource hikariDataSource = new HikariDataSource()
         hikariDataSource.setJdbcUrl(props.url)
         hikariDataSource.setUsername(props.username)
         hikariDataSource.setPassword(props.password)
         return hikariDataSource
         *//*/*return masterDataSourceProperties()
                 .initializeDataSourceBuilder()
                 .build()*//*/*
     }*//*

     @Bean
     @ConfigurationProperties("spring.datasource.master")
     DataSourceProperties masterDataSourceProperties() {
         return new DataSourceProperties()
     }

     @Bean
     @ConfigurationProperties("spring.datasource.slave")
     DataSourceProperties slaveDataSourceProperties() {
         return new DataSourceProperties()
     }*/
}