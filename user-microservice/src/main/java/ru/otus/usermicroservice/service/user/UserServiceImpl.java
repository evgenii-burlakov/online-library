package ru.otus.usermicroservice.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.usermicroservice.domain.Role;
import ru.otus.usermicroservice.domain.User;
import ru.otus.usermicroservice.exeption.ApplicationException;
import ru.otus.usermicroservice.repository.RoleRepository;
import ru.otus.usermicroservice.repository.UserRepository;
import ru.otus.usermicroservice.service.string.StringService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StringService stringService;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByUserName(String name) {
        return userRepository.findByUsername(name);
    }
    @Override
    @Transactional
    public User createWithUserRole(String name, String password) {
        String userName = stringService.beautifyStringName(name);
        String userPassword = stringService.beautifyStringName(password);

        if (!stringService.verifyNotBlank(userName, userPassword)) {
            throw new ApplicationException("Invalid user parameters");
        } else if (userRepository.findByUsername(userName).isPresent()) {
            throw new ApplicationException("User name already exist");
        } else {
            User newUser = userRepository.save(new User(null, userName, userPassword));
            roleRepository.save(new Role(null, "USER", newUser));
            return newUser;
        }
    }
}
