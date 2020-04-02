package com.westernacher.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends BaseEntity {

	@Column(length = 50)
	@Size(min = 1, max = 50)
	@NotNull
	private String firstName;

	@Column(length = 50)
	@Size(min = 1, max = 50)
	@NotNull
	private String lastName;

	@Column(columnDefinition = "TEXT")
	@NotNull(message = "email is required")
	@Email
	private String email;

	@Column(columnDefinition = "TEXT")
	@JsonFormat(pattern = "YYYY-MM-DD")
	@NotNull(message = "dateOfBirth is required")
	private LocalDate dateOfBirth;

	public User(String firstName, String lastName, String email, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
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
