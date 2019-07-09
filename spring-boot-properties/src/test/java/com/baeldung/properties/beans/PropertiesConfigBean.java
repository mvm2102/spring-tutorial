package com.baeldung.properties.beans;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfigBean {

    private Properties properties;

    public PropertiesConfigBean(@Autowired Properties properties) {
        this.properties = properties;
    }

    public String getProperty1() {
        return properties.getProperty("application.dynamic.prop1");
    }
}
