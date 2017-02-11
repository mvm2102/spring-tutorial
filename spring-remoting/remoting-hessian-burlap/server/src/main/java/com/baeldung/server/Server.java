package com.baeldung.server;

import com.baeldung.api.CabBookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Server {

    @Bean CabBookingService bookingService() {
        return new CabBookingServiceImpl();
    }

    @Bean(name = "/h_booking") HessianServiceExporter hessianService(CabBookingService service) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(bookingService());
        exporter.setServiceInterface( CabBookingService.class );
        return exporter;
    }

    @Bean(name = "/b_booking") BurlapServiceExporter burlapService(CabBookingService service) {
        BurlapServiceExporter exporter = new BurlapServiceExporter();
        exporter.setService(bookingService());
        exporter.setServiceInterface( CabBookingService.class );
        return exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}