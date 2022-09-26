package pl.sdacademy.springbootdatajpaexample.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sdacademy.springbootdatajpaexample.repository.UserRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityConfigTest implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.save(new User("user", "user", "user", passwordEncoder.encode("user"), "USER"));
//        repository.save(new User("admin", "admin", "admin", passwordEncoder.encode("admin"), "ADMIN"));

//        repository.save(new User("user2", "user2", "user2", passwordEncoder.encode("user2"), "USER"));
    }
}
