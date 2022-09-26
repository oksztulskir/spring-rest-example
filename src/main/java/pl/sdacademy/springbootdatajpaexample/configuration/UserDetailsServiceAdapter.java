package pl.sdacademy.springbootdatajpaexample.configuration;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sdacademy.springbootdatajpaexample.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .map(UserDetailsAdapter::new)
                .orElse(null);
    }
}
