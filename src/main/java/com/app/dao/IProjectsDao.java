package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Projects;

public interface IProjectsDao extends JpaRepository<Projects, Integer>{
 //search by project name
	Optional<Projects> findByProjectId(Integer projectId);
	
	@Query("select projectId from Projects")
	List<Integer> getProjectId();

}

