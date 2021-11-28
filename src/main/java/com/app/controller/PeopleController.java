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

import com.app.custom_excs.PersonNotFoundException;

import com.app.dao.IPeopleDao;
import com.app.dto.Email;
import com.app.dto.ErrorResponse;
import com.app.dto.Login;
import com.app.dto.ResponseDTO;
import com.app.pojos.People;
import com.app.service.MailService;



@RestController // => @Controller at class level +
//@ResponseBody annotation added on ret types of all req handling methods
@RequestMapping("/people")
@CrossOrigin(origins = "http://localhost:4200")
@Validated //To enable :  validation err handling on request params or path variables
public class PeopleController {
	// dependency: repository(DAO layer i/f)
	@Autowired
	private IPeopleDao peopledao;

	public PeopleController() {
		System.out.println("in ctor of " + getClass().getName());
	}
	@Autowired
	private MailService mailService;

	// RESTful end point or API end point or API provider
	@GetMapping
	public ResponseEntity<?> getAllPersonDetails() {
		System.out.println("in list all products");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<People>people = peopledao.findAll();
		
			// empty product list : set sts code : HTTP 204 (no contents)
			//return new ResponseEntity<>(projects, HttpStatus.OK);
			//return ResponseEntity.ok(people);//sts code : 200 , body : list of projects
		return new ResponseEntity<>(new ResponseDTO("success","List of all persons", people),HttpStatus.OK);
	}

	// get prduct details by its name : supplied by clnt using path var
	@GetMapping("/{personId}")
	public ResponseEntity<?> getPersonDetails(@PathVariable Integer personId) {
		System.out.println("in get person details " + personId);
		// invoke service method
		Optional<People> optional = peopledao.findByPersonId(personId);
		// valid name : HTTP 200 , marshalled product details
		if (optional.isPresent())
//			//return new ResponseEntity<>(optional.get(), HttpStatus.OK);
//		    return  ResponseEntity.ok(optional.get());
//		 // invalid id
//			ErrorResponse resp = new ErrorResponse("Person Name Invalid", "");
//			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
			 return new ResponseEntity<>(new ResponseDTO("success","details found",optional.get()),HttpStatus.OK);
		 // invalid id
			ErrorResponse resp = new ErrorResponse("Project Name Invalid", "");
			return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);
	}
	

	@GetMapping("/id")
	public ResponseEntity<?> getAllPersonIds() {
		System.out.println("in list all people");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<Integer>people = peopledao.getPersonId();
		
			// empty product list : set sts code : HTTP 204 (no contents)
			//return new ResponseEntity<>(projects, HttpStatus.OK);
			//return ResponseEntity.ok(projects);//sts code : 200 , body : list of projects
		return new ResponseEntity<>(new ResponseDTO("success","List of all persons", people),HttpStatus.OK);
	}
	
	
	
	// add new project details : Create : POST
	// @RequestBody : un marshalling (json ---> java obj)
	//plus validation rules will be invoked from the POJO.
	@PostMapping
	public ResponseEntity<?> addPerson(@RequestBody People person) {
		   System.out.println("in add person " + person);
			return new ResponseEntity<>(new ResponseDTO("success","person added succesfully",peopledao.save(person)),HttpStatus.CREATED);
		}
	
	// update existing projects details : post / put
	// http://host:port/projects/15
	// req handling method to update existing projects
//	@PutMapping("/{personId}")
//	public ResponseEntity<?> updatePersonDetails(@PathVariable int personId, @RequestBody People person) {
//		System.out.println("in update person details" + personId + " " + person);
//		// check if project exists
//				Optional<People> optional = peopledao.findByPersonId(personId);
//				if (optional.isPresent()) {
//					// project id valid : update the same
//					People existingPerson = optional.get();// DETACHED
//					System.out.println("existing person " + existingPerson);
//					//existingProject.setDesignation(p.getDesignation());
//					//existingProject.setSalary(p.getSalary());
//					// update detached POJO
//					
//					Optional<People>people=peopledao.findByPersonId(existingPerson.getPersonId());
//					if(optional.isPresent())
//					{
//						People p=people.get();
//						Email email= new Email();
//						email.setDestEmail(p.getPersonEmail());
//						System.out.println(p.getPersonEmail());
//						email.setSubject("Notification related to bugs");
//						email.setMessage("You have received a notification from your Project Manager related to bugs");
//						mailService.sendEmail(email);
//					}
//					
//					return new ResponseEntity<>(peopledao.save(existingPerson), HttpStatus.OK);
//					// save or update (insert: transient(value of ID : default
//					// or non default value BUT existing on DB -- update
//				} else
//					throw new PersonNotFoundException("Person ID Invalid");
//
//			}
	
	
	@PutMapping("/{personId}")
	public ResponseEntity<?> updatePersonDetails(@PathVariable int personId, @RequestBody People people) {
		System.out.println("in update issues" + personId + " " + people);
		// check if project exists
				Optional<People> optional = peopledao.findByPersonId(personId);
				if (optional.isPresent()) {
					// project id valid : update the same
					People existingPerson = optional.get();// DETACHED
					System.out.println("existing person " + existingPerson);
					//existingProject.setDesignation(p.getDesignation());
					//existingProject.setSalary(p.getSalary());
					existingPerson.setPersonName(people.getPersonName());
					existingPerson.setPassword(people.getPassword());
					existingPerson.setPersonEmail(people.getPersonEmail());
					existingPerson.setRole(people.getRole());
					existingPerson.setAssignedProjects(people.getAssignedProjects());
					existingPerson.setCreatedOn(people.getCreatedOn());
                    existingPerson.setCreatedBy(people.getCreatedBy());
                    existingPerson.setModifiedOn(people.getModifiedOn());
                    existingPerson.setModifiedBy(people.getModifiedBy());
                   
					// update detached POJO
					return new ResponseEntity<>(new ResponseDTO("success","Person record updated successfully",peopledao.save(existingPerson)), HttpStatus.OK);
					// save or update (insert: transient(value of ID : default
					// or non default value BUT existing on DB -- update
				} else
					return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);

			}


	
	
	//req handling method to delete existing project
	@DeleteMapping("/{personId}")
	public ResponseEntity<?> deletePersonDetails(@PathVariable  int personId) {
		System.out.println("in delete emp " + personId);
		// check if emp exists
		Optional<People> optional = peopledao.findByPersonId(personId);
		if (optional.isPresent()) {
			peopledao.deleteById(personId);
			return new ResponseEntity<>(new ResponseDTO("success","Person record deleted with ID " ,+ personId), HttpStatus.OK);
		} else
			 throw new PersonNotFoundException("Person ID Invalid : record deletion failed");
		//	throw new RuntimeException("my own err mesg");

	}
	
	@PostMapping("/login")
	public ResponseEntity<?> validatePeopleCredentials(@RequestBody Login login)
	{
		People people=peopledao.validatePeople(login.getPersonEmail(), login.getPassword());
		if(people==null)
		{
			return new ResponseEntity<>(new ResponseDTO("error","Invalid Credentials",null), HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<>(new ResponseDTO("success","Login Succesfully",people), HttpStatus.OK);
		}
	

}
	
	
	@PutMapping("/email/{personId}")
	public ResponseEntity<?> sendEmail(@PathVariable int personId, Email email) {
		System.out.println("in update person details" + personId );
		// check if project exists
				Optional<People> optional = peopledao.findByPersonId(personId);
				if (optional.isPresent()) {
					// project id valid : update the same
					People existingPerson = optional.get();// DETACHED
					
						email.setDestEmail(existingPerson.getPersonEmail());
						System.out.println(existingPerson.getPersonEmail());
						email.setSubject("You are assigned with new bug");
						email.setMessage("Dear User,you have been assigned with new bug.Kindly login to TechieSync for more details.");
						mailService.sendEmail(email);
					}
					
					
					//existingProject.setDesignation(p.getDesignation());
					//existingProject.setSalary(p.getSalary());
					// update detached POJO
					return new ResponseEntity<>(new ResponseDTO("success","Successfully mail sent",mailService), HttpStatus.OK);
					// save or update (insert: transient(value of ID : default
					// or non default value BUT existing on DB -- update
				}

	
	
	
}
