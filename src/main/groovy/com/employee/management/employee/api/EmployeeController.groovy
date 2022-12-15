package com.employee.management.employee.api

import com.employee.management.employee.entity.Employee
import com.employee.management.employee.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('employee')
class EmployeeController {

    private final EmployeeService employeeService

    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService
    }

    @GetMapping
    List<Employee> getEmployees() {
        return employeeService.fetchEmployees()
    }

    @GetMapping('/{id}')
    Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.fetchEmployeeById(id)
    }

    @PostMapping
    Employee getEmployeeById(@RequestBody Map<String, String> employeeData) {
        return employeeService.createEmployee(
                employeeData.firstName,
                employeeData.lastName,
                employeeData.middleName
        )
    }
}
