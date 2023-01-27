package com.baeldung.jndi;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.annotation.DirtiesContext;

import javax.naming.*;
import javax.sql.DataSource;

import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JndiUnitTest {

    private static InitialContext ctx;
    private static DriverManagerDataSource ds;

    @BeforeAll
    void setUp() throws Exception {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        ds = new DriverManagerDataSource("jdbc:h2:mem:mydb");
        builder.activate();

        JndiTemplate jndiTemplate = new JndiTemplate();
        ctx = (InitialContext) jndiTemplate.getContext();
    }

    @Test
    void givenACompositeName_whenAddingAnElement_thenNameIncludesIt() throws Exception {
//        JndiTemplate jndiTemplate = new JndiTemplate();
//        ctx = (InitialContext) jndiTemplate.getContext();
        Name objectName = new CompositeName("java:comp/env/jdbc");

        Enumeration<String> elements = objectName.getAll();
        while(elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }

        objectName.add("example");

        assertEquals("env", objectName.get(1));
        assertEquals("example", objectName.get(objectName.size() - 1));
    }

    @Test
    void givenADataSource_whenAddingDriver_thenBind() throws Exception {
//        JndiTemplate jndiTemplate = new JndiTemplate();
//        ctx = (InitialContext) jndiTemplate.getContext();

        ds.setDriverClassName("org.h2.Driver");
        ctx.bind("java:comp/env/jdbc/datasource", ds);
    }

    @Test
    void givenContext_whenLookupByName_thenValidDataSource() throws Exception {

//        JndiTemplate jndiTemplate = new JndiTemplate();
//        ctx = (InitialContext) jndiTemplate.getContext();

        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");

        assertNotNull(ds);
        assertNotNull(ds.getConnection());
    }

    @AfterAll
    void tearDown() throws Exception {
       ctx.close();
       ctx = null;
    }
}
