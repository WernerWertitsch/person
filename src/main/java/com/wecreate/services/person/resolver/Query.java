package com.wecreate.services.person.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wecreate.services.person.model.Person;
import com.wecreate.services.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private PersonRepository personRepository;


    public Iterable<Person> findAllPersons() {
        return this.personRepository.findAll();
    }

    public Long countPersons() {
        return this.personRepository.count();
    }

    public Iterable<Person> findFuzzyWholeName(String namePart) {
        return this.personRepository.findPeopleByFirstnameContainsOrLastnameContains(namePart, namePart);
    }

}
