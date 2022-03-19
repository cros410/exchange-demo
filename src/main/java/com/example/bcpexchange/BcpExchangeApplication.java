package com.example.bcpexchange;

import com.example.bcpexchange.context.user.application.UserService;
import com.example.bcpexchange.context.user.domain.Role;
import com.example.bcpexchange.context.user.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BcpExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BcpExchangeApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "USER_ROLE"));
            userService.saveRole(new Role(null, "ADMIN_ROLE"));

            userService.saveUser(new User(null, "User", "user", "123123", new ArrayList<>()));
            userService.saveUser(new User(null, "Admin", "admin", "123123", new ArrayList<>()));

            userService.addRoleToUser("user", "USER_ROLE");
            userService.addRoleToUser("admin", "ADMIN_ROLE");
        };
    }

}
