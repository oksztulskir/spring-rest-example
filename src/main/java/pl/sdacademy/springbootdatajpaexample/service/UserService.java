package pl.sdacademy.springbootdatajpaexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdacademy.springbootdatajpaexample.entity.User;
import pl.sdacademy.springbootdatajpaexample.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User find(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User with id=" + id + " not found!"));
    }

    public List<User> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User create(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return repository.save(user);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public User update(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return repository.save(user);
    }

    public User patchUpdate(User user) {
        User originalUser = find(user.getId());
        if (user.getFirstName() != null) {
            originalUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            originalUser.setLastName(user.getLastName());
        }
        if (user.getLogin() != null) {
            originalUser.setLogin(user.getLogin());
        }
        if (user.getPassword() != null) {
            originalUser.setPassword(encodePassword(user.getPassword()));
        }

        return repository.save(originalUser);
    }

    public Page<User> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
