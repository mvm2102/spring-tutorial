package com.baeldung;

import com.baeldung.api.BookingException;
import com.baeldung.api.CabBookingService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.lang.System.out;

@SpringBootApplication
public class AmqpClient {

    @Bean Queue queue() {
        Queue name = new Queue("remotingQueue");
        return name;
    }

    @Bean AmqpProxyFactoryBean amqpFactoryBean(AmqpTemplate amqpTemplate) {
        AmqpProxyFactoryBean factoryBean = new AmqpProxyFactoryBean();
        factoryBean.setServiceInterface(CabBookingService.class);
        factoryBean.setAmqpTemplate(amqpTemplate);
        return factoryBean;
    }

    @Bean Exchange directExchange(Queue someQueue) {
        DirectExchange exchange = new DirectExchange("remoting.exchange");
        Binding binding = BindingBuilder.bind(someQueue).to(exchange).with("remoting.binding");
        return exchange;
    }

    @Bean RabbitTemplate amqpTemplatexx(CachingConnectionFactory ccf) {
        RabbitTemplate template = new RabbitTemplate(ccf);
        template.setRoutingKey("remoting.binding");
        template.setExchange("remoting.exchange");
        return template;
    }

    public static void main(String[] args) throws BookingException {
        CabBookingService service = SpringApplication.run(AmqpClient.class, args).getBean(CabBookingService.class);
        out.println(service.bookRide("13 Seagate Blvd, Key Largo, FL 33037"));
    }

}
