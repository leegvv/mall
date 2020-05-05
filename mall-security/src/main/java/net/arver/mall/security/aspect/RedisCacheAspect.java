package net.arver.mall.security.aspect;

import net.arver.mall.security.annotation.CacheException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(2)
public class RedisCacheAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("execution(public * net.arver.mall.portal.service.*CacheService.*(..)) || execution(public * net.arver.mall.service.*CacheService.*(..))")
    public void cacheAspect(){}

    @Around("cacheAspect()")
    public Object doAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Signature signature = joinPoint.getSignature();
        final MethodSignature methodSignature = (MethodSignature) signature;
        final Method method = methodSignature.getMethod();
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (final Throwable throwable) {
            //有CacheException注解的方法需要抛出异常
            if (method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            } else {
                LOGGER.error(throwable.getMessage());
            }
        }
        return result;
    }

}
