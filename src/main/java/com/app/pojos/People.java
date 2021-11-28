package com.app.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")

@Entity
@Table(name = "people")
public class People implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personId")
	private Integer personId;
	@Column(length = 30)
	private String personName;
	@Column(length = 30)
	private String password;
	@Column(length = 30, unique = true)
	private String personEmail;
	@Enumerated(EnumType.STRING)
	private PersonRole role;
	private int assignedProjects;
	@Column(name = "createdOn")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "createdOn")
	private LocalDate createdOn;
	private String createdBy;
	@Column(name = "modifiedOn")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "modifiedOn")
	private LocalDate modifiedOn;
	private String modifiedBy;
	
	@OneToMany(mappedBy = "people",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("people")
	private List<Issues>issues=new ArrayList<>();

	

	public People() {
		System.out.println("in "+getClass().getName());
	}
	
	public People(Integer personId, String personName, String password, String personEmail, PersonRole role,
			int assignedProjects, LocalDate createdOn, String createdBy, LocalDate modifiedOn, String modifiedBy) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.password = password;
		this.personEmail = personEmail;
		this.role = role;
		this.assignedProjects = assignedProjects;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public PersonRole getRole() {
		return role;
	}

	public void setRole(PersonRole role) {
		this.role = role;
	}

	public int getAssignedProjects() {
		return assignedProjects;
	}

	public void setAssignedProjects(int assignedProjects) {
		this.assignedProjects = assignedProjects;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDate modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "People [personId=" + personId + ", personName=" + personName + ", password=" + password
				+ ", personEmail=" + personEmail + ", role=" + role + ", assignedProjects=" + assignedProjects
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", modifiedOn=" + modifiedOn
				+ ", modifiedBy=" + modifiedBy + "]";
	}


}