package com.baeldung.socket;

import org.apache.catalina.startup.Tomcat;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.SocketUtils;

import java.io.IOException;
import java.net.ServerSocket;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FindAvailablePortUnitTest {

    private static int FREE_PORT_NUMBER;
    private static int[] FREE_PORT_RANGE;

    @BeforeAll
    public static void getFreePortAndRange() {
        try (ServerSocket serverSocket = new ServerSocket(0);) {
            FREE_PORT_NUMBER = serverSocket.getLocalPort();
            FREE_PORT_RANGE = new int[] {FREE_PORT_NUMBER, FREE_PORT_NUMBER + 1, FREE_PORT_NUMBER + 2};
        } catch (IOException e) {
            fail("No free port is not available");
        }
    }

    @Test
    public void givenExplicitFreePort_whenCreatingServerSocket_thenPortIsAssigned() {
        try (ServerSocket serverSocket = new ServerSocket(FREE_PORT_NUMBER);) {
            assertThat(serverSocket).isNotNull();
            assertThat(serverSocket.getLocalPort()).isEqualTo(FREE_PORT_NUMBER);
        } catch (IOException e) {
            fail("Port is not available");
        }
    }

    @Test
    public void givenExplicitOccupiedPort_whenCreatingServerSocket_thenExceptionIsThrown() {
        try (ServerSocket serverSocket = new ServerSocket(FREE_PORT_NUMBER)) {
            new ServerSocket(FREE_PORT_NUMBER);
            fail("Same port cannot be used twice");
        } catch (IOException e) {
            assertThat(e).hasMessageContaining("Address already in use: bind");
        }
    }

    @Test
    public void givenExplicitPortRange_whenCreatingServerSocket_thenPortIsAssigned() {
        for (int port : FREE_PORT_RANGE) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                assertThat(serverSocket).isNotNull();
                assertThat(serverSocket.getLocalPort()).isBetween(FREE_PORT_RANGE[0], FREE_PORT_RANGE[FREE_PORT_RANGE.length - 1]);
                return;
            } catch (IOException e) {
                assertThat(e).hasMessageContaining("Address already in use: bind");
            }
        }
        fail("No free port in the range found");
    }

    @Test
    public void givenPortZero_whenCreatingServerSocket_thenFreePortIsAllocated() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            assertThat(serverSocket).isNotNull();
            assertThat(serverSocket.getLocalPort()).isGreaterThan(0);
        } catch (IOException e) {
            fail("Port is not available");
        }
    }

    @Test
    public void givenAvailableTcpPort_whenCreatingServerSocket_thenPortIsAssigned() {
        int port = SocketUtils.findAvailableTcpPort();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            assertThat(serverSocket).isNotNull();
            assertThat(serverSocket.getLocalPort()).isGreaterThan(0);
        } catch (IOException e) {
            fail("Port is not available");
        }
    }

    @Test
    public void givenNoPortDefined_whenCreatingJettyServer_thenFreePortIsAllocated() {
        Server jettyServer = new Server();
        ServerConnector serverConnector = new ServerConnector(jettyServer);
        jettyServer.addConnector(serverConnector);
        try {
            jettyServer.start();
            assertThat(serverConnector.getLocalPort()).isGreaterThan(0);
            jettyServer.stop();
        } catch (Exception e) {
            fail("Failed to start Jetty server");
        }
    }

    @Test
    public void givenExplicitFreePort_whenCreatingJettyServer_thenPortIsAssigned() {
        Server jettyServer = new Server();
        ServerConnector serverConnector = new ServerConnector(jettyServer);
        serverConnector.setPort(FREE_PORT_NUMBER);
        jettyServer.addConnector(serverConnector);
        try {
            jettyServer.start();
            assertThat(serverConnector.getLocalPort()).isEqualTo(FREE_PORT_NUMBER);
            jettyServer.stop();
        } catch (Exception e) {
            fail("Failed to start Jetty server");
        }
    }

    @Test
    public void givenNoPortDefined_whenCreatingTomcatServer_thenFreePortIsAllocated() {
        Tomcat tomcatServer = new Tomcat();
        try {
            tomcatServer.start();
            assertThat(tomcatServer.getConnector().getLocalPort()).isGreaterThan(0);
            tomcatServer.stop();
        } catch (Exception e) {
            fail("Failed to start Tomcat server");
        }
    }

    @Test
    public void givenExplicitFreePort_whenCreatingTomcatServer_thenPortIsAssigned() {
        Tomcat tomcatServer = new Tomcat();
        tomcatServer.setPort(FREE_PORT_NUMBER);
        try {
            tomcatServer.start();
            assertThat(tomcatServer.getConnector().getLocalPort()).isGreaterThan(0);
            tomcatServer.stop();
        } catch (Exception e) {
            fail("Failed to start Tomcat server");
        }
    }

}
