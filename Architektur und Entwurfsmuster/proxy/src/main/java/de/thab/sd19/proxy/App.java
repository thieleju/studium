package de.thab.sd19.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args) {
//        @SuppressWarnings("unchecked")
//        List<String> proxy = (List<String>) Proxy.newProxyInstance(App.class.getClassLoader(), 
//                               new Class[] {List.class}, 
//                               new TraceProxy(new LinkedList<String>()));
//        
//        proxy.add("Test 1.");
//        proxy.add("Test 2.");
//        proxy.add("Test 3.");
        
        Subject sub = (Subject) Proxy.newProxyInstance(App.class.getClassLoader(), 
                new Class[] {Subject.class}, 
                new AccessProxy(new SubjectImpl()));
        
        sub.test();
        sub.test12();
        
    }
}
