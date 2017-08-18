package com.baeldung.setterdi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/setterdi.xml")
public class SetterBeanInjectionXMLConfigUnitTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void givenXMLConfig_whenApplicationStarts_thenSpringShouldCreateCarWithDependencies() {
        DependencyInjectionTestHelper.checkCarInitializedWithDependencies(context);
    }
}
