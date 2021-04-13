package com.baeldung.pattern.hexagonal.dao;

import com.baeldung.pattern.hexagonal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("prod")
public class UserProviderImpl implements UserProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(String login) {
        return userRepository.getByLogin(login)
                .map(User::fromEntity);
    }
}
