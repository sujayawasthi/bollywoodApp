package com.boll.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.boll.model.Actor;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "actor", path = "actor")
public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {

	List<Actor> findByName(@Param("name") String name);

}
