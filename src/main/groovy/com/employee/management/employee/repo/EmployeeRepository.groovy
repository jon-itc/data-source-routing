package com.employee.management.employee.repo

import com.employee.management.employee.entity.Employee
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
