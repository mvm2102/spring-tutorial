package com.baeldung.java.reflection;

import java.lang.annotation.Annotation;

/**
 * @author zn.wang
 */
public class DynamicGreeter implements Greeter {
    
    private String greet;

    public DynamicGreeter(String greet) {
        this.greet = greet;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return DynamicGreeter.class;

    }

    @Override
    public String greet() {
        return greet;
    }

}
