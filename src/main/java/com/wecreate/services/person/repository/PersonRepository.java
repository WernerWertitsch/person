package com.wecreate.services.person.repository;

import com.wecreate.services.person.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {

    @RestResource(path="/fuzzy")
    public Iterable<Person> findPeopleByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(@Param(value = "n1") String firstname, @Param(value="n2") String lastName);
}
