package com.baeldung.multibeaninstantiation.solution2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.baeldung.multibeaninstantiation.solution2.Person;
import com.baeldung.multibeaninstantiation.solution2.PersonConfig;

public class SpringApp2 {
    public static void main(String[] args) {
        // Initializing the spring container
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersonConfig.class);

        Person person = context.getBean("personTwo", Person.class);

        System.out.println(person);

        context.close();
    }
}