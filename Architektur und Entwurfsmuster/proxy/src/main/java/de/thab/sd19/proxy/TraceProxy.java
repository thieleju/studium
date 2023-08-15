package de.thab.sd19.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraceProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(TraceProxy.class);
    
    private final Object target; 
    
    public TraceProxy(Object target) {
        this.target = target;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getClass().getName();
        String methodName = method.getName();
        
        log.trace("Entering method {}.{}", className, methodName);
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long duration = start - System.nanoTime();
        log.trace("Leaving method {}.{}. Took {} ns.", className, methodName, duration);
        return result;
    }
 }
