package com.employee.management.config.datasource

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "datasource.slave")
class SlaveDataSourceProperties {
    String url
    String username
    String password
}
