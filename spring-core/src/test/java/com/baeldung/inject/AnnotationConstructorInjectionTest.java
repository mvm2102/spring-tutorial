package com.baeldung.inject;

import com.baeldung.inject.annotation.constructor.AnotherService;
import com.baeldung.inject.annotation.constructor.ApplicationConfig;
import com.baeldung.inject.annotation.constructor.SomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

/**
 * @author Oleg Cherednik
 * @since 27.01.2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class AnnotationConstructorInjectionTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void someServiceShouldInjectSpecificAnotherServices() {
        SomeService someService = context.getBean(SomeService.class);
        AnotherService a = context.getBean("a", AnotherService.class);
        AnotherService b = context.getBean("b", AnotherService.class);

        assertNotSame(a, b);
        assertSame(a, someService.getA());
        assertSame(b, someService.getB());
    }

}
