package com.wecreate.services.person.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.wecreate.services.person.model.Person;
import com.wecreate.services.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Mutation implements GraphQLMutationResolver {

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
        ret.setDateCreated(new Date());
        try {
            if(birthDate!=null) {
                ret.setBirthdate(new SimpleDateFormat(Person.DATE_FORMAT).parse(birthDate));
            }
        } catch (ParseException e) {
            logger.warning(String.format("Could not parse Date %s, for new Person %s %s - not setting birthdate", birthDate, lastname, firstname));
            e.printStackTrace();
        }
        ret.setEmail(email);
        ret.setPhone(phone);
        this.personRepository.save(ret);
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