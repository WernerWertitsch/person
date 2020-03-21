package com.wecreate.services.person.repository;

import com.wecreate.services.person.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
    public Iterable<Person> findPeopleByFirstnameContainsOrLastnameContains(String firstName, String lastName);
}
