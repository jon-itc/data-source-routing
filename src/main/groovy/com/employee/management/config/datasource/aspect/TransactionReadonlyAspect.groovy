package com.employee.management.config.datasource.aspect

import com.employee.management.config.datasource.DatabaseContextHolder
import com.employee.management.config.datasource.DatabaseEnvironment
import groovy.util.logging.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Aspect
@Component
@Order(0)
@Slf4j
class TransactionReadonlyAspect {

    @Around("@annotation(transactional)")
    Object proceed(ProceedingJoinPoint proceedingJoinPoint, Transactional transactional) throws Throwable {
        try {
            if (transactional.readOnly()) {
                DatabaseContextHolder.set(DatabaseEnvironment.READONLY)
            }
            return proceedingJoinPoint.proceed()
        } finally {
            DatabaseContextHolder.reset()
        }
    }
}