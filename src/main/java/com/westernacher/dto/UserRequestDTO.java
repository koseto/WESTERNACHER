package com.westernacher.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserRequestDTO implements Serializable {

	private static final long serialVersionUID = -3782750985165520171L;

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

	public UserRequestDTO(String firstName, String lastName, String email, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
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
}
