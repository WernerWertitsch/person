package com.wecreate.services.person.repository;

import com.wecreate.services.person.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface PersonRepository extends MongoRepository<Person, String> {
    public Iterable<Person> findPeopleByFirstnameContainsOrLastnameContains(String firstName, String lastName);
    public Iterable<Person> findPeopleByLastnameAndDateCreatedAfter(String lastName, Date birthdateBefore);
}
