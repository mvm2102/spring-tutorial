package com.baeldung.commons.lang3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.concurrent.Future;

import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.arch.Processor;
import org.apache.commons.lang3.concurrent.ConcurrentRuntimeException;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.apache.commons.lang3.event.EventUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Assert;
import org.junit.Test;

public class Lang3UtilsTest {

    @Test
    public void test_to_Boolean_fromString() {
        assertFalse(BooleanUtils.toBoolean("off"));
        assertTrue(BooleanUtils.toBoolean("true"));
        assertTrue(BooleanUtils.toBoolean("tRue"));
        assertFalse(BooleanUtils.toBoolean("no"));
        assertFalse(BooleanUtils.isTrue(Boolean.FALSE));
        assertFalse(BooleanUtils.isTrue(null));
    }

    @Test
    public void testGetUserHome() {
        final File dir = SystemUtils.getUserHome();
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
    }

    @Test
    public void testGetJavaHome() {
        final File dir = SystemUtils.getJavaHome();
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
    }

    @Test
    public void testProcessorArchType() {
        Processor processor = ArchUtils.getProcessor("x86");
        assertTrue(processor.is32Bit());
        assertFalse(processor.is64Bit());
    }

    @Test
    public void testProcessorArchType64Bit() {
        Processor processor = ArchUtils.getProcessor("x86_64");
        assertFalse(processor.is32Bit());
        assertTrue(processor.is64Bit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConcurrentRuntimeExceptionCauseError() {
        new ConcurrentRuntimeException("An error", new Error());
    }

    @Test
    public void testConstantFuture_Integer() throws Exception {
        Future<Integer> test = ConcurrentUtils.constantFuture(5);
        assertTrue(test.isDone());
        assertSame(5, test.get());
        assertFalse(test.isCancelled());
    }

    @Test
    public void testFieldUtilsGetAllFields() {
        final Field[] fieldsNumber = Number.class.getDeclaredFields();
        assertArrayEquals(fieldsNumber, FieldUtils.getAllFields(Number.class));
    }

    @Test
    public void test_getInstance_String_Locale() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.US);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);

        assertNotSame(format1, format3);
    }

    @Test
    public void testAddEventListenerThrowsException() {
        final ExceptionEventSource src = new ExceptionEventSource();
        try {
            EventUtils.addEventListener(src, PropertyChangeListener.class, new PropertyChangeListener() {
                @Override
                public void propertyChange(final PropertyChangeEvent e) {
                    // Do nothing!
                }
            });
            fail("Add method should have thrown an exception, so method should fail.");
        } catch (final RuntimeException e) {

        }
    }

    public static class ExceptionEventSource {
        public void addPropertyChangeListener(final PropertyChangeListener listener) {
            throw new RuntimeException();
        }
    }

}
