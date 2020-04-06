package com.westernacher.dto;

import com.westernacher.model.User;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -3782750985165520171L;

    private Long id;

    @ApiModelProperty(required = true, allowableValues = "range[1, 50]")
    @NotBlank(message = "firstName may not be blank.")
    @Size(min = 1, max = 50, message = "firstName must be between 1 and 50 characters.")
    private String firstName;

    @ApiModelProperty(required = true, allowableValues = "range[1, 50]")
    @NotBlank(message = "lastName may not be blank.")
    @Size(min = 1, max = 50, message = "lastName must be between 1 and 50 characters.")
    private String lastName;

    @ApiModelProperty(required = true)
    @NotBlank(message = "email may not be blank.")
    @Email(message = "email should be valid.")
    private String email;

    @ApiModelProperty(required = true, example = "1989-09-11")
    @Past
    private LocalDate dateOfBirth;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.dateOfBirth = user.getDateOfBirth();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static List<UserDTO> responseUsersDTOFromCollection(Iterable<User> users) {
        List<UserDTO> response = new ArrayList<>();
        for (User user : users) {
            response.add(new UserDTO(user));
        }
        return response;
    }
}
