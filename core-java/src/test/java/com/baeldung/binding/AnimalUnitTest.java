package com.baeldung.binding;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by madhumita.g on 01-08-2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class AnimalUnitTest {
    @Mock
    private Appender mockAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Before
    public void setup() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(mockAppender);
    }

    @After
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAppender(mockAppender);
    }

    @Test
    public void overloadinTesting() {

        Animal animal = new Animal();

        animal.makeNoise();

        verify(mockAppender).doAppend(captorLoggingEvent.capture());

        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();

        assertThat(loggingEvent.getLevel(), is(Level.INFO));

        assertThat(loggingEvent.getFormattedMessage(),
                is("generic animal noise"));

        animal.makeNoise(3);

        assertThat(loggingEvent.getLevel(), is(Level.INFO));

        assertThat(loggingEvent.getFormattedMessage(),
                is("generic animal noise countdown 3\n"
                        + "generic animal noise countdown 2\n"
                        + "generic animal noise countdown 1\n"));

    }

}
