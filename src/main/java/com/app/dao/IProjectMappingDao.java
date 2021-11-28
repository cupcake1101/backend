package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.ProjectMapping;

public interface IProjectMappingDao extends JpaRepository<ProjectMapping, Integer>{
 //search by project name
	Optional<ProjectMapping> findByprojectMappingid(Integer projectMappingid);
}
