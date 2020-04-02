package com.westernacher.service;

import com.westernacher.model.User;
import com.westernacher.repository.UserRepository;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth
    ) throws Exception {
        User user = getUserByEmail(email);
        if (user == null) {
            user = new User(firstName, lastName, email, dateOfBirth);

            user = userRepository.save(user);
            log.info("User with id: " + user.getId() + " saved.");
            log.info("Preferences for user with id: " + user.getId());
            return user;
        } else {
            return null;
        }
    }

    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        return user;
    }

    /**
     * <b>id</b> must not be null.
     * If <b>id</b> is <b>null</b> , response is <b>null</b>
     *
     * @param id
     * @throws Exception
     */
    public void deleteUserById(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAllUser() throws Exception {
        return userRepository.findAll();
    }
}