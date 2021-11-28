package com.app.pojos;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonProperty;
@SuppressWarnings("serial")
@Embeddable
@Entity
@Table(name="projectMapping")

public class ProjectMapping implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectMappingid;
	
	
	
	@OneToOne
	@JoinColumn(name="projectId")
	private Projects projects;
	
	@OneToOne
	@JoinColumn(name="personId")
	private People people;
	
	
	
	@Column(name = "startDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "startDate")
	private LocalDate startDate;
	
	@Column(name = "endDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "endDate")
	private LocalDate endDate;
	
	

	public ProjectMapping() {
		System.out.println("in "+getClass().getName());
	}

	public ProjectMapping(Integer projectMappingid, Projects projects, People people, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.projectMappingid = projectMappingid;
		this.projects = projects;
		this.people = people;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getProjectMappingid() {
		return projectMappingid;
	}

	public void setProjectMappingid(Integer projectMappingid) {
		this.projectMappingid = projectMappingid;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ProjectMapping [projectMappingid=" + projectMappingid + ", projects=" + projects + ", people=" + people
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	
	
	
}