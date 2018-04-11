package com.baeldung.log4j2.logtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LogTest {

    static {
        PluginManager.addPackage("com.baeldung.log4j2.plugins");
    }

    @Test
    public void simpleProgrammaticConfiguration() {
        Logger logger = LogManager.getLogger();
        Marker markerContent = MarkerManager.getMarker("FLOW");
        logger.debug(markerContent, "Debug log message");
        logger.info(markerContent, "Info log message");
        logger.error(markerContent, "Error log message");
    }
}
