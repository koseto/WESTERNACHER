package com.westernacher.controller;

import com.westernacher.ServerResponse;
import com.westernacher.dto.UserDTO;
import com.westernacher.dto.UserRequestDTO;
import com.westernacher.model.User;
import com.westernacher.service.UserService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @ResponseBody
    public ServerResponse<UserDTO> createUserByEmail(@RequestBody UserRequestDTO userRequest) {
        User user = null;
        UserDTO userDTO = null;
        try {
            user = userService.addUser(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getDateOfBirth()
            );
            if (user == null) {
                String error = "Error the User already exists.";
                ServerResponse<UserDTO> serverResponse = new ServerResponse<UserDTO>(false, error);
                return serverResponse;
            } else {
                userDTO = new UserDTO(user);
                ServerResponse<UserDTO> serverResponse = new ServerResponse<UserDTO>(true, userDTO);
                log.info("New User [id : %s] is added.", user.getId());
                return serverResponse;
            }
        } catch (Exception ex) {
            log.error(ex.getStackTrace().toString());
            String error = "Error creating the User.";
            ServerResponse<UserDTO> serverResponse = new ServerResponse<UserDTO>(false, error);
            return serverResponse;
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public ServerResponse<UserDTO> deleteUserById(
        @PathVariable(value = "userId") Long userId
    ) {
        try {
            userService.deleteUserById(userId);
            ServerResponse<UserDTO> serverResponse = new ServerResponse<>(true);
            log.info("An User [id : %s] is deleted.", userId);
            return serverResponse;
        } catch (Exception ex) {
            String error = "Error Deleting the User.";
            ServerResponse<UserDTO> serverResponse = new ServerResponse<>(false, error);
            return serverResponse;
        }
    }

    @GetMapping
    @ResponseBody
    public ServerResponse<List<UserDTO>> getAllUsers() {
        Iterable<User> users = null;
        try {
            users = userService.getAllUser();
            if (users == null) {
                String error = "Error The list of Users is empty.";
                ServerResponse<List<UserDTO>> serverResponse = new ServerResponse<>(false, error);
                return serverResponse;
            } else {
                ServerResponse<List<UserDTO>> serverResponse = new ServerResponse<>(true,
                    UserDTO.responseUsersDTOFromCollection(users));
                return serverResponse;
            }
        } catch (Exception ex) {
            String error = "Error fetching all Users.";
            ServerResponse<List<UserDTO>> serverResponse = new ServerResponse<>(false, error);
            return serverResponse;
        }
    }
}