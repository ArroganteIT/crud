package com.sinko.appCRUD3.dao;

import com.sinko.appCRUD3.models.Person;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class PersonDAO {

    private List<Person> people;

    private int id_counter;

    {
        people = new ArrayList<>();
    }

    public void save(Person person) {
        person.setId(++id_counter);
        people.add(person);
    }

    public void update(int id, Person newPerson) {
        Person updatedPerson = getPerson(id);
        updatedPerson.setName(newPerson.getName());
        updatedPerson.setAge(newPerson.getAge());
        updatedPerson.setEmail(newPerson.getEmail());
    }

    public void delete(int id) {
        Person forDeletePerson = getPerson(id);
        people.remove(forDeletePerson);
    }


    public List<Person> getPeople() {
        return people;
    }

    public Person getPerson(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
