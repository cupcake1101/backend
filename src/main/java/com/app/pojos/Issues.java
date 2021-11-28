package com.app.pojos;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@SuppressWarnings("serial")
@Embeddable

@Entity
@Table(name="issues")
public class Issues implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer issueId;
	private String issueName;
	private String issueDescription;
	//private Integer identifiedBypersonId;//foreignKey
//	@Column(name = "identifiedDate")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonProperty(value = "identifiedDate")
//	private LocalDate identifiedDate;
	//private Integer relatedProjects;
	//private Integer assignedTo;
	 @Enumerated(EnumType.STRING)
	private issueStatus status;
	 @Enumerated(EnumType.STRING)
	private issuePriority priority;
	 @Enumerated(EnumType.STRING)
	private issueSeverity severity;
	@Column(name = "targetResolutionDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value = "targetResolutionDate")
	private LocalDate targetResolutionDate;
	//private String progress;
//	@Column(name = "actualResolutionDate")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonProperty(value = "actualResolutionDate")
//	private LocalDate actualResolutionDate;
	private String resolutionSummary;
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
	//private Integer personId;
	//private Integer projectId;
	
	
	@ManyToOne
	private People people;
	
	@ManyToOne
	@JsonIgnoreProperties("issues")
	private Projects projects;
	
	@OneToMany(targetEntity=Projects.class)
	private List projectslist;
     
	
	public Issues() {
		System.out.println("in "+getClass().getName());
	}
	

	public Issues(Integer issueId, String issueName, String issueDescription, LocalDate identifiedDate,
			issueStatus status, issuePriority priority, issueSeverity severity, LocalDate targetResolutionDate,
			String progress, String resolutionSummary, LocalDate createdOn,
			String createdBy, LocalDate modifiedOn, String modifiedBy, People people, Projects projects,
			List projectslist) {
		super();
		this.issueId = issueId;
		this.issueName = issueName;
		this.issueDescription = issueDescription;
		//this.identifiedDate = identifiedDate;
		this.status = status;
		this.priority = priority;
		this.severity = severity;
		this.targetResolutionDate = targetResolutionDate;
		//this.progress = progress;
		//this.actualResolutionDate = actualResolutionDate;
		this.resolutionSummary = resolutionSummary;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
		this.people = people;
		this.projects = projects;
		this.projectslist = projectslist;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

//	public LocalDate getIdentifiedDate() {
//		return identifiedDate;
//	}
//
//	public void setIdentifiedDate(LocalDate identifiedDate) {
//		this.identifiedDate = identifiedDate;
//	}

	public issueStatus getStatus() {
		return status;
	}

	public void setStatus(issueStatus status) {
		this.status = status;
	}

	public issuePriority getPriority() {
		return priority;
	}

	public void setPriority(issuePriority priority) {
		this.priority = priority;
	}

	public issueSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(issueSeverity severity) {
		this.severity = severity;
	}

	public LocalDate getTargetResolutionDate() {
		return targetResolutionDate;
	}

	public void setTargetResolutionDate(LocalDate targetResolutionDate) {
		this.targetResolutionDate = targetResolutionDate;
	}

//	public String getProgress() {
//		return progress;
//	}
//
//	public void setProgress(String progress) {
//		this.progress = progress;
//	}

//	public LocalDate getActualResolutionDate() {
//		return actualResolutionDate;
//	}
//
//	public void setActualResolutionDate(LocalDate actualResolutionDate) {
//		this.actualResolutionDate = actualResolutionDate;
//	}

	public String getResolutionSummary() {
		return resolutionSummary;
	}

	public void setResolutionSummary(String resolutionSummary) {
		this.resolutionSummary = resolutionSummary;
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

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	public List getProjectslist() {
		return projectslist;
	}

	public void setProjectslist(List projectslist) {
		this.projectslist = projectslist;
	}

	@Override
	public String toString() {
		return "Issues [issueId=" + issueId + ", issueSummary=" + issueName + ", issueDescription="
				+ issueDescription + ", status=" + status + ", priority="
				+ priority + ", severity=" + severity + ", targetResolutionDate=" + targetResolutionDate + ", resolutionSummary="
				+ resolutionSummary + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", modifiedOn="
				+ modifiedOn + ", modifiedBy=" + modifiedBy + ", people=" + people + ", projects=" + projects
				+ ", projectslist=" + projectslist + "]";
	}
	
	
	
	


}

//
//
//
//package com.app.pojos;
//
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//@SuppressWarnings("serial")
//@Embeddable
//
//@Entity
//@Table(name="issues")
//public class Issues implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JsonProperty("id")
//	private Integer issueId;
//	private String issueName;
//	private String issueDescription;
//	//private Integer identifiedBypersonId;//foreignKey
////	@Column(name = "identifiedDate")
////	@DateTimeFormat(pattern = "yyyy-MM-dd")
////	@JsonProperty(value = "identifiedDate")
////	private LocalDate identifiedDate;
//	//private Integer relatedProjects;
//	//private Integer assignedTo;
//	 @Enumerated(EnumType.STRING)
//	private issueStatus status;
//	 @Enumerated(EnumType.STRING)
//	private issuePriority priority;
//	 @Enumerated(EnumType.STRING)
//	private issueSeverity severity;
//	@Column(name = "targetResolutionDate")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonProperty(value = "targetResolutionDate")
//	private LocalDate targetResolutionDate;
//	//private String progress;
////	@Column(name = "actualResolutionDate")
////	@DateTimeFormat(pattern = "yyyy-MM-dd")
////	@JsonProperty(value = "actualResolutionDate")
////	private LocalDate actualResolutionDate;
//	private String resolutionSummary;
//	@Column(name = "createdOn")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonProperty(value = "createdOn")
//	private LocalDate createdOn;
//	private String createdBy;
//	@Column(name = "modifiedOn")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonProperty(value = "modifiedOn")
//	private LocalDate modifiedOn;
//	private String modifiedBy;
//	//private Integer personId;
//	//private Integer projectId;
//	
//	
//	@ManyToOne
//	private People people;
//	
//	@ManyToOne
//	private Projects projects;
//	
////	@OneToMany(targetEntity=Projects.class)
////	private List projectslist;
//     
//	
//	public Issues() {
//		System.out.println("in "+getClass().getName());
//	}
//	
//
//	public Issues(Integer issueId, String issueName, String issueDescription, LocalDate identifiedDate,
//			issueStatus status, issuePriority priority, issueSeverity severity, LocalDate targetResolutionDate,
//			String progress, String resolutionSummary, LocalDate createdOn,
//			String createdBy, LocalDate modifiedOn, String modifiedBy, People people, Projects projects,
//			List projectslist) {
//		super();
//		this.issueId = issueId;
//		this.issueName = issueName;
//		this.issueDescription = issueDescription;
//		//this.identifiedDate = identifiedDate;
//		this.status = status;
//		this.priority = priority;
//		this.severity = severity;
//		this.targetResolutionDate = targetResolutionDate;
//		//this.progress = progress;
//		//this.actualResolutionDate = actualResolutionDate;
//		this.resolutionSummary = resolutionSummary;
//		this.createdOn = createdOn;
//		this.createdBy = createdBy;
//		this.modifiedOn = modifiedOn;
//		this.modifiedBy = modifiedBy;
//		this.people = people;
//		this.projects = projects;
//		//this.projectslist = projectslist;
//	}
//
//	public Integer getIssueId() {
//		return issueId;
//	}
//
//	public void setIssueId(Integer issueId) {
//		this.issueId = issueId;
//	}
//
//	public String getIssueName() {
//		return issueName;
//	}
//
//	public void setIssueName(String issueName) {
//		this.issueName = issueName;
//	}
//
//	public String getIssueDescription() {
//		return issueDescription;
//	}
//
//	public void setIssueDescription(String issueDescription) {
//		this.issueDescription = issueDescription;
//	}
//
////	public LocalDate getIdentifiedDate() {
////		return identifiedDate;
////	}
////
////	public void setIdentifiedDate(LocalDate identifiedDate) {
////		this.identifiedDate = identifiedDate;
////	}
//
//	public issueStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(issueStatus status) {
//		this.status = status;
//	}
//
//	public issuePriority getPriority() {
//		return priority;
//	}
//
//	public void setPriority(issuePriority priority) {
//		this.priority = priority;
//	}
//
//	public issueSeverity getSeverity() {
//		return severity;
//	}
//
//	public void setSeverity(issueSeverity severity) {
//		this.severity = severity;
//	}
//
//	public LocalDate getTargetResolutionDate() {
//		return targetResolutionDate;
//	}
//
//	public void setTargetResolutionDate(LocalDate targetResolutionDate) {
//		this.targetResolutionDate = targetResolutionDate;
//	}
//
////	public String getProgress() {
////		return progress;
////	}
////
////	public void setProgress(String progress) {
////		this.progress = progress;
////	}
//
////	public LocalDate getActualResolutionDate() {
////		return actualResolutionDate;
////	}
////
////	public void setActualResolutionDate(LocalDate actualResolutionDate) {
////		this.actualResolutionDate = actualResolutionDate;
////	}
//
//	public String getResolutionSummary() {
//		return resolutionSummary;
//	}
//
//	public void setResolutionSummary(String resolutionSummary) {
//		this.resolutionSummary = resolutionSummary;
//	}
//
//	public LocalDate getCreatedOn() {
//		return createdOn;
//	}
//
//	public void setCreatedOn(LocalDate createdOn) {
//		this.createdOn = createdOn;
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public LocalDate getModifiedOn() {
//		return modifiedOn;
//	}
//
//	public void setModifiedOn(LocalDate modifiedOn) {
//		this.modifiedOn = modifiedOn;
//	}
//
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public People getPeople() {
//		return people;
//	}
//
//	public void setPeople(People people) {
//		this.people = people;
//	}
//
//	public Projects getProjects() {
//		return projects;
//	}
//
//	public void setProjects(Projects projects) {
//		this.projects = projects;
//	}
//
////	public List getProjectslist() {
////		return projectslist;
////	}
////
////	public void setProjectslist(List projectslist) {
////		this.projectslist = projectslist;
////	}
//
//	@Override
//	public String toString() {
//		return "Issues [issueId=" + issueId + ", issueSummary=" + issueName + ", issueDescription="
//				+ issueDescription + ", status=" + status + ", priority="
//				+ priority + ", severity=" + severity + ", targetResolutionDate=" + targetResolutionDate + ", resolutionSummary="
//				+ resolutionSummary + ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", modifiedOn="
//				+ modifiedOn + ", modifiedBy=" + modifiedBy + ", people=" + people + ", projects=" + projects
//				+ "]";
//	}
//	
//	
//	
//	
//
//
//}