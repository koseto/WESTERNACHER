package com.westernacher.controller;

import com.westernacher.ServerResponse;
import com.westernacher.dto.UserDTO;
import com.westernacher.dto.UserRequestDTO;
import com.westernacher.model.User;
import com.westernacher.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseRestController {

    private Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Create user with unique email.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "New user is created and returned."),
        @ApiResponse(code = 400, message = "The request body is not correct.")}
    )
    public ServerResponse<UserDTO> createUserByEmail(
        @Valid @RequestBody UserRequestDTO userRequest) {
        try {
            UserDTO userDTO = userService.addUser(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getDateOfBirth()
            );
            if (userDTO == null) {
                String error = "Error the User already exists.";
                ServerResponse<UserDTO> serverResponse = new ServerResponse<UserDTO>(false, error);
                return serverResponse;
            } else {
                ServerResponse<UserDTO> serverResponse = new ServerResponse<UserDTO>(true, userDTO);
                log.info("New User [id : %s] is added.", userDTO.getId());
                return serverResponse;
            }
        } catch (Exception ex) {
            log.error(ex.getStackTrace().toString());
            String error = "Error creating the User.";
            ServerResponse<UserDTO> serverResponse = new ServerResponse<UserDTO>(false, error);
            return serverResponse;
        }
    }

    @ApiOperation(value = "Delete user by id.")
    @DeleteMapping("/{userId}")
    @ResponseBody
    public ServerResponse<UserDTO> deleteUserById(@PathVariable(value = "userId") Long userId) {
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

    @ApiOperation(value = "List sorted users.")
    @GetMapping
    @ResponseBody
    public ServerResponse<List<UserDTO>> getAllUsers(
        @ApiParam(allowableValues = "firstName, lastName, email")
        @RequestParam(value = "sort-by", required = false, defaultValue = "firstName") String sortBy,
        @RequestParam(value = "sort-direction", required = false, defaultValue = "ASC") Direction sortDirection
    ) {
        try {
            Iterable<User> users = userService.getAllUser(sortBy, sortDirection);
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