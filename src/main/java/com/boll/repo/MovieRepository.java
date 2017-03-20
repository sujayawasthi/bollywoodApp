package com.boll.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.boll.model.Movie;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

	List<Movie> findByName(@Param("name") String name);

	List<Movie> findByEra(@Param("era") String era);

}
