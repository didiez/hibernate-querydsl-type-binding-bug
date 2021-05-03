package es.didiez.hibernatequerydslbug;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner populateUsers(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("John", true, true));
            userRepository.save(new User("Jane", true, false));
            userRepository.save(new User("Jim", false, false));
        };
    }

}
