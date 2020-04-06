package com.westernacher.service;

import com.westernacher.dto.UserDTO;
import com.westernacher.model.User;
import com.westernacher.repository.UserRepository;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users
 */
@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addUser(
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth
    ) {
        if (userRepository.findByEmail(email) == null) {
            User createdUser = userRepository
                .save(new User(firstName, lastName, email, dateOfBirth));
            log.info("Created user with id: " + createdUser.getId());
            return new UserDTO(createdUser);
        } else {
            return null;
        }
    }

    /**
     * <b>id</b> must not be null.
     * If <b>id</b> is <b>null</b> , response is <b>null</b>
     *
     * @param id
     * @throws Exception
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }
}