//package com.app.dao;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.app.pojos.People;
//
//
//public interface IPeopleDao extends JpaRepository<People, Integer>{
// //search by project name
//	Optional<People> findByPersonId(Integer personId);
//	@Query("select personId from People")
//	List<Integer> getPersonId();	
//}
//

package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.People;



public interface IPeopleDao extends JpaRepository<People, Integer>{
 //search by project name
	Optional<People> findByPersonId(Integer personId);
	@Query("select personId from People")
	List<Integer> getPersonId();
	
	
	
	@Query("select people from People people where people.personEmail= :personEmail and people.password= :password")
	People validatePeople(String personEmail,String password );
}