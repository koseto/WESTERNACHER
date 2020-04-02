package com.westernacher.model;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long id;

	@NotNull
	private Timestamp createdAt;

	private Timestamp updatedAt;

	public Long getId() {

		return this.id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	public void updateCreatedAt() {
		Timestamp today = new Timestamp(new java.util.Date().getTime());
		setCreatedAt(today);
	}

	@PreUpdate
	public void updateUpdatedAt() {
		Timestamp today = new Timestamp(new java.util.Date().getTime());
		setUpdatedAt(today);
	}
}
