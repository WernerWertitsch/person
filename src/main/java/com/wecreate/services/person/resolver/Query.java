package com.wecreate.services.person.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wecreate.services.person.model.Person;
import com.wecreate.services.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public Iterable<Person> findByLastNameAndCreatedAtAfter(String lastname, String dateCreatedAfter) throws ParseException {
        Date dateObj = new SimpleDateFormat(Mutation.INCOMING_DATE_FORMAT).parse(dateCreatedAfter);
        return this.personRepository.findPeopleByLastnameAndDateCreatedAfter(lastname, dateObj);
    }



}
