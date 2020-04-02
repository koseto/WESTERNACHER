package com.westernacher.dto;

import com.westernacher.model.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -3782750985165520171L;

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
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
