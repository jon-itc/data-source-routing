package com.employee.management.config.datasource.aspect

import com.employee.management.config.datasource.DatabaseContextHolder
import com.employee.management.config.datasource.DatabaseEnvironment
import groovy.util.logging.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.CannotCreateTransactionException
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
                return executeReadOnlyTransactionWithFallBack(proceedingJoinPoint)
            }
            return proceedingJoinPoint.proceed()
        } finally {
            DatabaseContextHolder.reset()
        }
    }

    private static Object executeReadOnlyTransactionWithFallBack(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            DatabaseContextHolder.set(DatabaseEnvironment.READONLY)
            return proceedingJoinPoint.proceed()
        } catch (CannotCreateTransactionException e) {
            log.warn("Unable to connect to read only replica, attempting master connection.", e.message)
            DatabaseContextHolder.set(DatabaseEnvironment.UPDATABLE)
            return proceedingJoinPoint.proceed()
        }
    }
}