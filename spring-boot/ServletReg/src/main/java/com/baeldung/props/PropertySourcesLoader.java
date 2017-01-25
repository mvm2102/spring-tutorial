package com.baeldung.props;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

/**
@Configuration
@PropertySource("classpath:/java/com/baeldung/props/custom.properties") */
public class PropertySourcesLoader {

        /**private static final Logger log = LoggerFactory.getLogger(PropertySourcesLoader.class);

        @Autowired
        ConfigurableEnvironment env;

        public String getProperty(String key) {
                return env.getProperty(key);
        } */
}
