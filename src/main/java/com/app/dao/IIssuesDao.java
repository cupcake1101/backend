

package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Issues;


public interface IIssuesDao extends JpaRepository<Issues, Integer>{
 //search by project name
	Optional<Issues> findByIssueId(Integer issueId );
	@Query("select severity, count(*) from Issues group by severity")
	List<Object[]> getSeverities();
	
}