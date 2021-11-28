package com.app.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@SuppressWarnings("serial")

@Entity
@Table(name="projects")
public class Projects implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	@Column(length = 30)
	private String projectName;
	@Column(name = "startDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "startDate")
	private LocalDate startDate;
	@Column(name = "targetEndDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "targetEndDate")
	private LocalDate targetEndDate;
	@Column(name = "actualEndDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "actualEndDate")
	private LocalDate actualEndDate;
	@Column(name = "createdOn")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "createdOn")
	private LocalDate createdOn;
	@Column(length = 30)
	private String createdBy;
	@Column(name = "modifiedOn")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "modifiedOn")
	private LocalDate modifiedOn;
	@Column(length = 30)
	private String modifiedBy;
	@OneToMany(mappedBy = "projects",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("projects")
	private List<Issues>issues=new ArrayList<>();
	
	public Projects() {
		// TODO Auto-generated constructor stub
		System.out.println("in "+getClass().getName());
	}
	
	public Projects(Integer projectId, String projectName, LocalDate startDate, LocalDate targetEndDate,
			LocalDate actualEndDate, LocalDate createdOn, String createdBy, LocalDate modifiedOn, String modifiedBy) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.targetEndDate = targetEndDate;
		this.actualEndDate = actualEndDate;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getTargetEndDate() {
		return targetEndDate;
	}
	public void setTargetEndDate(LocalDate targetEndDate) {
		this.targetEndDate = targetEndDate;
	}
	public LocalDate getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(LocalDate actualEndDate) {
		this.actualEndDate = actualEndDate;
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
		return "Projects [projectId=" + projectId + ", projectName=" + projectName + ", startDate=" + startDate
				+ ", targetEndDate=" + targetEndDate + ", actualEndDate=" + actualEndDate + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", modifiedOn=" + modifiedOn + ", modifiedBy=" + modifiedBy + "]";
	}
	
	
	
	
	
	
}