package com.chartmycash;

import com.chartmycash.entity.User;
import com.chartmycash.repositary.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChartMyCashApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChartMyCashApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepo) {
        return args -> {
            try {
                User sampleUser = new User(
                        "John", "Doe", "john.doe@example.com",
                        "Secure@123",   // ✅ Includes uppercase, lowercase, digit, special char
                        "johnny", 25
                );
                userRepo.save(sampleUser);
                System.out.println("✅ Saved user:");
                userRepo.findAll().forEach(System.out::println);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Validation failed: " + e.getMessage());
            }
        };
    }

}
