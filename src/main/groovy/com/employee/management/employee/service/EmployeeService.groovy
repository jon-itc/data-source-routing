package com.employee.management.employee.service

import com.employee.management.api.exception.DataNotFoundException
import com.employee.management.employee.entity.Employee
import com.employee.management.employee.entity.EmployeeType
import com.employee.management.employee.entity.Position
import com.employee.management.employee.repo.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeService {

    private final EmployeeRepository employeeRepository

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository
    }

    @Transactional(readOnly = true)
    List<Employee> fetchEmployees() {
        return employeeRepository.findAll().toList()
    }

    @Transactional(readOnly = true)
    Employee fetchEmployeeById(Long id) {
        employeeRepository.findById(id).orElseThrow { new DataNotFoundException("Employee [${id}] not found") }
    }

    @Transactional
    Employee createEmployee(String firstName,
                            String lastName,
                            String middleName) {
        employeeRepository.save(
                new Employee(
                        firstName: firstName,
                        lastName: lastName,
                        middleName: middleName,
                        position: Position.OTHER,
                        employeeType: EmployeeType.FULL_TIME
                )
        )
    }
}
