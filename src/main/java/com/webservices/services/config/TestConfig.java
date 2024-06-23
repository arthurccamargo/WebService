package com.webservices.services.config;

import com.webservices.services.entities.Order;
import com.webservices.services.entities.User;
import com.webservices.services.repositories.OrderRepository;
import com.webservices.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test") // test name same as application.properties
public class TestConfig implements CommandLineRunner { // run this application only test profile
    @Autowired // to Spring resolve dependency and associate instance UserRepository in TestConfig
    private UserRepository userRepository; // access data

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Marcia Brown", "marcia@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Arthur Green", "arthur@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
