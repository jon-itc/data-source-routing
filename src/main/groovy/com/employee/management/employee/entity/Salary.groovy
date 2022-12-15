package com.employee.management.employee.entity


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_seq")
    Long id

    Long amount
}
