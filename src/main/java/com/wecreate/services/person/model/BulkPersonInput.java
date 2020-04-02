package com.wecreate.services.person.model;

import java.util.List;

public class BulkPersonInput {
    List<PersonInput> persons;

    public List<PersonInput> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonInput> persons) {
        this.persons = persons;
    }
}
