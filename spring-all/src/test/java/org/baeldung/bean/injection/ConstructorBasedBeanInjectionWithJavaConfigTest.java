package org.baeldung.bean.injection;

import org.baeldung.bean.config.ConstructorBasedFinalDependencyShipConfig;
import org.baeldung.bean.config.ConstructorBasedShipConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorBasedBeanInjectionWithJavaConfigTest {
    private static final String HELM_NAME = "HelmBrand";

    @Test
    public void givenJavaConfigFile_whenUsingConstructorBasedBeanInjection_thenCorrectHelmName() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConstructorBasedShipConfig.class);
        ctx.refresh();

        Ship ship = ctx.getBean(Ship.class);

        Assert.assertEquals(HELM_NAME, ship.getHelm()
            .getBrandOfHelm());
    }

    @Test
    public void givenJavaConfigFile_whenUsingConstructorBasedFinalBeanInjection_thenCorrectHelmName() {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConstructorBasedFinalDependencyShipConfig.class);
        ctx.refresh();

        ShipWithFinalDependency ship = ctx.getBean(ShipWithFinalDependency.class);

        Assert.assertEquals(HELM_NAME, ship.getHelm()
            .getBrandOfHelm());
    }
}
