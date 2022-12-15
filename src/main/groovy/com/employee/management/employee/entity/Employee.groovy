package com.employee.management.employee.entity

import javax.persistence.*

@Entity
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    Long id

    String firstName
    String lastName
    String middleName

    @Enumerated(EnumType.STRING)
    Position position
    @Enumerated(EnumType.STRING)
    EmployeeType employeeType
}
