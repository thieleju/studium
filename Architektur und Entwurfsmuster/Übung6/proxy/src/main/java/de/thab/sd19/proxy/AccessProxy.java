package de.thab.sd19.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(AccessProxy.class);
    
    private final Object target; 
    
    public AccessProxy(Object target) {
        this.target = target;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getClass().getName();
        String methodName = method.getName();
        
        LocalTime time = LocalTime.now();
        int minute = time.getMinute();
        
        String suffix = methodName.substring(methodName.length() - 2, methodName.length() );
        System.out.println("suffix: " + suffix);
        
        try {
            int number = Integer.parseInt(suffix);
            if (number != minute ) {
                log.error("Access violation. Wrong time.");
                throw new RuntimeException("Not now!");
            }
        } catch (NumberFormatException e) {
            log.trace("suffix {} is not a number.", suffix);
        }
        
        return method.invoke(target, args);
    }
 }
