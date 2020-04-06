package com.westernacher.service

import com.westernacher.repository.UserRepository
import com.westernacher.model.User
import java.time.LocalDate
import spock.lang.Specification

class UserServiceSpec extends Specification {

    private static final LocalDate validDateOfBirth = LocalDate.parse("1989-09-11");
    private static final LocalDate futureDateOfBirth = LocalDate.parse("9999-12-31");

    UserRepository userRepository
    UserService userService

    def setup() {
        userRepository = Mock()

        userService = new UserService(userRepository)
    }

    def 'createUser with existing email'() {
        when:
        def user = userService.addUser(_ as String, _ as String, _ as String, validDateOfBirth)

        then:
        1 * userRepository.findByEmail(_ as String) >> new User();
        user == null
    }
}
