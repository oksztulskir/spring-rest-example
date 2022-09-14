package pl.sdacademy.springbootdatajpaexample.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRepositoryTest implements CommandLineRunner {
    private final UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        User user = new User("Jan", "Kowalski", "kowalj", "password");
//        log.info("Saved user in db: " + repository.save(user));

//        List<User> users = Arrays.asList(
//                new User("Jan", "Kowalski", "kowalj", "password"),
//                new User("Tomasz", "Nowak", "nowakt", "password1"),
//                new User("Joanna", "Nowak", "nowakj", "password2"),
//                new User("Krystyna", "Kowalska", "kowalk", "password3"),
//                new User("John", "Doe", "doej", "password4")
//        );
//
//        repository.saveAll(users);
//
//        repository.findAll().forEach(user -> log.info("User: " + user));

//        repository.findByFirstNameAndLastNameLike("Joann", "owa").forEach(user -> log.info(user.toString()));

//            repository.deleteUserByLogin("nowakt");

//        long id = 1L;
//        Optional<User> existingUser = repository.findById(id);
//        existingUser.ifPresent(user -> {
//            log.info("Existing user with id={} found: {}", id, user);
//            user.setPassword("password_2");
//            User updatedUser = repository.save(user);
//            log.info("Updated user: " + updatedUser);
//        });
//
//        log.info("Deleting user by id={}", id);
//        repository.deleteById(id);
    }
}
