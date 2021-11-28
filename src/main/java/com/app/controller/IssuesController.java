package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_excs.IssuesNotFoundException;
import com.app.dao.IIssuesDao;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Issues;
import com.app.pojos.Projects;


@RestController // => @Controller at class level +
//@ResponseBody annotation added on ret types of all req handling methods
@RequestMapping("/bugs")
@CrossOrigin(origins = "http://localhost:4200")
@Validated //To enable :  validation err handling on request params or path variables
public class IssuesController {
	// dependency: repository(DAO layer i/f)
	@Autowired
	private IIssuesDao issuesdao;

	public IssuesController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	// RESTful end point or API end point or API provider
	@GetMapping
	public ResponseEntity<?> getAllIssueDetails() {
		System.out.println("in list all products");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<Issues>issues = issuesdao.findAll();
		
			// empty product list : set sts code : HTTP 204 (no contents)
			//return new ResponseEntity<>(projects, HttpStatus.OK);
			//return ResponseEntity.ok(issues);//sts code : 200 , body : list of projects
		return new ResponseEntity<>(new ResponseDTO("success","List of all issues", issues),HttpStatus.OK);
	}

	// get prduct details by its name : supplied by clnt using path var
	@GetMapping("/{issueId}")
	public ResponseEntity<?> getIssueDetails(@PathVariable Integer issueId) {
		System.out.println("in get proj details " + issueId);
		// invoke service method
		Optional<Issues> optional = issuesdao.findByIssueId(issueId);
		// valid name : HTTP 200 , marshalled product details
		if (optional.isPresent())
			//return new ResponseEntity<>(optional.get(), HttpStatus.OK);
//		    return  ResponseEntity.ok(optional.get());
//		 // invalid id
//			ErrorResponse resp = new ErrorResponse("Issue Id Invalid", "");
//			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
			 return new ResponseEntity<>(new ResponseDTO("success","details found",optional.get()),HttpStatus.OK);
		 // invalid id
			ErrorResponse resp = new ErrorResponse("Issue Name Invalid", "");
			return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);
	}

	// add new project details : Create : POST
	// @RequestBody : un marshalling (json ---> java obj)
	//plus validation rules will be invoked from the POJO.
	@PostMapping
	public ResponseEntity<?> addIssue(@RequestBody Issues issue) {
		   System.out.println("in add issue " + issue);
			//return new ResponseEntity<>(issuesdao.save(issue),HttpStatus.CREATED);
		   return new ResponseEntity<>(new ResponseDTO("success","issues added successfully",issuesdao.save(issue)),HttpStatus.CREATED);

		}
	
	// update existing projects details : post / put
	// http://host:port/projects/15
	// req handling method to update existing projects
	@PutMapping("/{issueId}")
	public ResponseEntity<?> updateIssueDetails(@PathVariable int issueId, @RequestBody Issues issue) {
		System.out.println("in update issues" + issueId + " " + issue);
		// check if project exists
				Optional<Issues> optional = issuesdao.findByIssueId(issueId);
				if (optional.isPresent()) {
					// project id valid : update the same
					Issues existingIssues = optional.get();// DETACHED
					System.out.println("existing project " + existingIssues);
					//existingProject.setDesignation(p.getDesignation());
					//existingProject.setSalary(p.getSalary());
					existingIssues.setIssueName(issue.getIssueName());
					existingIssues.setIssueDescription(issue.getIssueDescription());
					existingIssues.setStatus(issue.getStatus());
					existingIssues.setPriority(issue.getPriority());
					existingIssues.setSeverity(issue.getSeverity());
					existingIssues.setTargetResolutionDate(issue.getTargetResolutionDate());
                    existingIssues.setResolutionSummary(issue.getResolutionSummary());
                    existingIssues.setCreatedOn(issue.getCreatedOn());
                    existingIssues.setCreatedBy(issue.getCreatedBy());
                    existingIssues.setModifiedOn(issue.getModifiedOn());
            	    existingIssues.setModifiedBy(issue.getModifiedBy());
                    existingIssues.setPeople(issue.getPeople());
            		existingIssues.setProjects(issue.getProjects());
					// update detached POJO
					return new ResponseEntity<>(new ResponseDTO("success","Issue recorded updated with Id",issuesdao.save(existingIssues)), HttpStatus.OK);
					// save or update (insert: transient(value of ID : default
					// or non default value BUT existing on DB -- update
				} else
					return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);

			}
	
	//req handling method to delete existing project
	@DeleteMapping("/{issueId}")
	public ResponseEntity<?> deleteIssueDetails(@PathVariable  int issueId) {
		System.out.println("in delete emp " + issueId);
		// check if emp exists
		Optional<Issues> optional = issuesdao.findByIssueId(issueId);
		if (optional.isPresent()) {
			issuesdao.deleteById(issueId);
			return new ResponseEntity<>(new ResponseDTO("success","Issue record deleted with ID ", + issueId), HttpStatus.OK);
		} else
			 throw new IssuesNotFoundException("Issue ID Invalid : record deletion failed");
		//	throw new RuntimeException("my own err mesg");

	}
	
	@GetMapping("/severity")
	public ResponseEntity<?> getSeverities() {
		System.out.println("in list all issues");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<Object[]>issues = issuesdao. getSeverities();
		

		
			// empty product list : set sts code : HTTP 204 (no contents)
			//return new ResponseEntity<>(projects, HttpStatus.OK);
			//return ResponseEntity.ok(issues);//sts code : 200 , body : list of projects
		return new ResponseEntity<>(new ResponseDTO("success","List of all severity", issues),HttpStatus.OK);
	}
	

}























//package com.app.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.app.custom_excs.IssuesNotFoundException;
//import com.app.dao.IIssuesDao;
//import com.app.dto.ErrorResponse;
//import com.app.dto.ResponseDTO;
//import com.app.pojos.Issues;
//import com.app.pojos.Projects;
//
//
//@RestController // => @Controller at class level +
////@ResponseBody annotation added on ret types of all req handling methods
//@RequestMapping("/issues")
//@CrossOrigin(origins = "http://localhost:4200")
//@Validated //To enable :  validation err handling on request params or path variables
//public class IssuesController {
//	// dependency: repository(DAO layer i/f)
//	@Autowired
//	private IIssuesDao issuesdao;
//
//	public IssuesController() {
//		System.out.println("in ctor of " + getClass().getName());
//	}
//
//	// RESTful end point or API end point or API provider
//	@GetMapping
//	public ResponseEntity<?> getAllIssueDetails() {
//		System.out.println("in list all products");
//		// invoke service layer's method : controller --> service impl (p) --->JPA
//		// repo's impl class(SC)
//		List<Issues>issues = issuesdao.findAll();
//		
//			// empty product list : set sts code : HTTP 204 (no contents)
//			//return new ResponseEntity<>(projects, HttpStatus.OK);
//			//return ResponseEntity.ok(issues);//sts code : 200 , body : list of projects
//		return new ResponseEntity<>(new ResponseDTO("success","List of all issues", issues),HttpStatus.OK);
//	}
//
//	// get prduct details by its name : supplied by clnt using path var
//	@GetMapping("/{issueId}")
//	public ResponseEntity<?> getIssueDetails(@PathVariable Integer issueId) {
//		System.out.println("in get proj details " + issueId);
//		// invoke service method
//		Optional<Issues> optional = issuesdao.findByIssueId(issueId);
//		// valid name : HTTP 200 , marshalled product details
//		if (optional.isPresent())
//			//return new ResponseEntity<>(optional.get(), HttpStatus.OK);
////		    return  ResponseEntity.ok(optional.get());
////		 // invalid id
////			ErrorResponse resp = new ErrorResponse("Issue Id Invalid", "");
////			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
//			 return new ResponseEntity<>(new ResponseDTO("success","details found",optional.get()),HttpStatus.OK);
//		 // invalid id
//			ErrorResponse resp = new ErrorResponse("Project Name Invalid", "");
//			return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);
//	}
//
//	// add new project details : Create : POST
//	// @RequestBody : un marshalling (json ---> java obj)
//	//plus validation rules will be invoked from the POJO.
//	@PostMapping
//	public ResponseEntity<?> addIssue(@RequestBody Issues issue) {
//		   System.out.println("in add issue " + issue);
//			//return new ResponseEntity<>(issuesdao.save(issue),HttpStatus.CREATED);
//		   return new ResponseEntity<>(new ResponseDTO("success","issues added successfully",issuesdao.save(issue)),HttpStatus.CREATED);
//
//		}
//	
//	// update existing projects details : post / put
//	// http://host:port/projects/15
//	// req handling method to update existing projects
//	@PutMapping("/{issueId}")
//	public ResponseEntity<?> updateIssueDetails(@PathVariable int issueId, @RequestBody Issues issue) {
//		System.out.println("in update issues" + issueId + " " + issue);
//		// check if project exists
//				Optional<Issues> optional = issuesdao.findByIssueId(issueId);
//				if (optional.isPresent()) {
//					// project id valid : update the same
//					Issues existingIssues = optional.get();// DETACHED
//					System.out.println("existing project " + existingIssues);
//					//existingProject.setDesignation(p.getDesignation());
//					//existingProject.setSalary(p.getSalary());
//					existingIssues.setIssueName(issue.getIssueName());
//					existingIssues.setIssueDescription(issue.getIssueDescription());
//					existingIssues.setStatus(issue.getStatus());
//					existingIssues.setPriority(issue.getPriority());
//					existingIssues.setSeverity(issue.getSeverity());
//					//existingIssues.setTargetResolutionDate(issue.getTargetResolutionDate());
//                     //existingIssues.setResolutionSummary(issue.getResolutionSummary());
//                     existingIssues.setCreatedOn(issue.getCreatedOn());
//                     existingIssues.setCreatedBy(issue.getCreatedBy());
//                    //existingIssues.setModifiedOn(issue.getModifiedOn());
//            	    //existingIssues.setModifiedBy(issue.getModifiedBy());
//                    existingIssues.setPeople(issue.getPeople());
//            		existingIssues.setProjects(issue.getProjects());
//					// update detached POJO
//					return new ResponseEntity<>(new ResponseDTO("success","Issue recorded updated with Id",issuesdao.save(existingIssues)), HttpStatus.OK);
//					// save or update (insert: transient(value of ID : default
//					// or non default value BUT existing on DB -- update
//				} else
//					return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);
//
//			}
//	
//	//req handling method to delete existing project
//	@DeleteMapping("/{issueId}")
//	public ResponseEntity<?> deleteIssueDetails(@PathVariable  int issueId) {
//		System.out.println("in delete emp " + issueId);
//		// check if emp exists
//		Optional<Issues> optional = issuesdao.findByIssueId(issueId);
//		if (optional.isPresent()) {
//			issuesdao.deleteById(issueId);
//			return new ResponseEntity<>(new ResponseDTO("success","Issue record deleted with ID ", + issueId), HttpStatus.OK);
//		} else
//			 throw new IssuesNotFoundException("Issue ID Invalid : record deletion failed");
//		//	throw new RuntimeException("my own err mesg");
//
//	}
//	
//
//}
