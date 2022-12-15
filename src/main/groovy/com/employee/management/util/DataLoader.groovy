package com.employee.management.util

import com.employee.management.employee.entity.Employee
import com.employee.management.employee.repo.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component

import static com.employee.management.employee.entity.EmployeeType.FULL_TIME
import static com.employee.management.employee.entity.EmployeeType.TEMP
import static com.employee.management.employee.entity.Position.*

@Component
@DependsOn("dataSourceRouting")
class DataLoader implements CommandLineRunner {

    @Autowired
    EmployeeRepository repository

    @Override
    void run(String... args) throws Exception {
        repository.saveAll(
                [
                        new Employee(firstName: 'Nicolas', lastName: 'Gonzalez', position: CLERK, employeeType: TEMP),
                        new Employee(firstName: 'Jon', lastName: 'Trejo', position: MANAGER, employeeType: FULL_TIME),
                        new Employee(firstName: 'Dan', lastName: 'Debo', position: ADMIN, employeeType: FULL_TIME),
                        new Employee(firstName: 'Lauren', middleName: 'Nicole', lastName: 'Trejo', position: HUMAN_RESOURCE, employeeType: FULL_TIME)
                ]
        )
    }
}
