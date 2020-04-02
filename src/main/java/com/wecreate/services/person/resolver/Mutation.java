package com.wecreate.services.person.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.wecreate.services.person.model.BulkPersonInput;
import com.wecreate.services.person.model.Person;
import com.wecreate.services.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Mutation implements GraphQLMutationResolver {

    public static final String INCOMING_DATE_FORMAT = "yyyy-MM-dd hh:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INCOMING_DATE_FORMAT);

    Logger logger = Logger.getLogger(Mutation.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person createPerson(String lastname,
                               String firstname,
                               String birthDate,
                               List<String> email, List<String> phone) {
        Person ret = new Person();
        ret.setLastname(lastname);
        ret.setFirstname(firstname);
        ret.setDateCreated(LocalDate.now());
        try {
            if(birthDate!=null) {
                ret.setBirthdate(LocalDate.parse(birthDate, formatter));
            }
        } catch (Exception e) {
            logger.warning(String.format("Could not parse Date %s, for new Person %s %s - not setting birthdate", birthDate, lastname, firstname));
            e.printStackTrace();
        }
        ret.setEmail(email);
        ret.setPhone(phone);
        this.personRepository.save(ret);
        return ret;
    }

    public List<Person> createPersons(BulkPersonInput persons) {
        final List<Person> ret = new ArrayList<>();
        persons.getPersons().forEach(p -> {
            Person person = createPerson(p.getLastname(), p.getFirstname(), p.getBirthdate(), p.getEmail(), p.getPhone());
            ret.add(person);
        });
        return ret;
    }


    public boolean deletePerson(String id) {
        this.personRepository.deleteById(id);
        return true;
    }

    public void updatePerson(Person person) {
        this.personRepository.save(person);
    }
}