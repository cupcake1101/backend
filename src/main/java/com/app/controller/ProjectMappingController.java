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

import com.app.custom_excs.ProjectMappingNotFoundException;
import com.app.custom_excs.ProjectsNotFoundException;
import com.app.dao.IProjectMappingDao;
import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.ProjectMapping;


@RestController // => @Controller at class level +
//@ResponseBody annotation added on ret types of all req handling methods
@RequestMapping("/projectsmap")
@CrossOrigin(origins = "http://localhost:4200")
@Validated //To enable :  validation err handling on request params or path variables
public class ProjectMappingController {
	// dependency: repository(DAO layer i/f)
	@Autowired
	private IProjectMappingDao projectsmappingdao;

	public ProjectMappingController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	// RESTful end point or API end point or API provider
	@GetMapping
	public ResponseEntity<?> getAllProjectMappingDetails() {
		System.out.println("in list all projects");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<ProjectMapping>projectmapping = projectsmappingdao.findAll();
		
			// empty product list : set sts code : HTTP 204 (no contents)
			//return new ResponseEntity<>(projects, HttpStatus.OK);
			//return ResponseEntity.ok(projectmapping);//sts code : 200 , body : list of projects
		return new ResponseEntity<>(new ResponseDTO("success","List of all projectsmapping", projectmapping),HttpStatus.OK);
	}

	// get prduct details by its name : supplied by clnt using path var
	@GetMapping("/{projectmappingid}")
	public ResponseEntity<?> getProjectDetails(@PathVariable Integer projectMappingid) {
		System.out.println("in get proj details " + projectMappingid);
		
		// invoke service method
		Optional<ProjectMapping> optional = projectsmappingdao.findByprojectMappingid(projectMappingid);
		// valid name : HTTP 200 , marshalled product details
		if (optional.isPresent())
			//return new ResponseEntity<>(optional.get(), HttpStatus.OK);
//		    return  ResponseEntity.ok(optional.get());
//		 // invalid id
//			ErrorResponse resp = new ErrorResponse("ProjectMapping Name Invalid", "");
//			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
			 return new ResponseEntity<>(new ResponseDTO("success","details found",optional.get()),HttpStatus.OK);
		 // invalid id
			ErrorResponse resp = new ErrorResponse("Project Name Invalid", "");
			return new ResponseEntity<>(new ResponseDTO("error","details not found",null),HttpStatus.OK);
	}
	

	// add new project details : Create : POST
	// @RequestBody : un marshalling (json ---> java obj)
	//plus validation rules will be invoked from the POJO.
	@PostMapping
	public ResponseEntity<?> addProjectMapping(@RequestBody ProjectMapping projectmap) {
		   System.out.println("in add product " + projectmap);
			//return new ResponseEntity<>(projectsmappingdao.save(projectmap),HttpStatus.CREATED);
		   return new ResponseEntity<>(new ResponseDTO("success","projectmapping added successfully",projectsmappingdao.save(projectmap)),HttpStatus.CREATED);

		}
	
	// update existing projects details : post / put
	// http://host:port/projects/15
	// req handling method to update existing projects
	@PutMapping("/{projectmappingId}")
	public ResponseEntity<?> updateProjectMappingDetails(@PathVariable int projectMappingId, @RequestBody ProjectMapping projectmap) {
		System.out.println("in update projects" + projectMappingId + " " + projectmap);
		// check if project exists
				Optional<ProjectMapping> optional = projectsmappingdao.findByprojectMappingid(projectMappingId);
				if (optional.isPresent()) {
					// project id valid : update the same
					ProjectMapping existingProjectMapping = optional.get();// DETACHED
					System.out.println("existing projectmapping " + existingProjectMapping);
					//existingProject.setDesignation(p.getDesignation());
					//existingProject.setSalary(p.getSalary());
					// update detached POJO
					return new ResponseEntity<>(projectsmappingdao.save(existingProjectMapping), HttpStatus.OK);
					// save or update (insert: transient(value of ID : default
					// or non default value BUT existing on DB -- update
				} else
					throw new ProjectMappingNotFoundException("Project ID Invalid");

			}
	
	//req handling method to delete existing project
	@DeleteMapping("/{projectmappingId}")
	public ResponseEntity<?> deleteProjectMappingDetails(@PathVariable  int projectMappingId) {
		System.out.println("in delete emp " + projectMappingId);
		// check if emp exists
		Optional<ProjectMapping> optional = projectsmappingdao.findByprojectMappingid(projectMappingId);
		if (optional.isPresent()) {
			projectsmappingdao.deleteById(projectMappingId);
			return new ResponseEntity<>(new ResponseDTO("success","ProjectMapping record deleted with ID ", + projectMappingId), HttpStatus.OK);
		} else
			 throw new ProjectsNotFoundException("ProjectMapping ID Invalid : record deletion failed");
		//	throw new RuntimeException("my own err mesg");

	}
	

}
