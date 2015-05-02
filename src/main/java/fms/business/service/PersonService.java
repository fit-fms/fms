package fms.business.service;

import fms.business.form.Admin;
import fms.business.form.Person;

import java.util.List;

public interface PersonService {

    public void createPerson(Person person) throws Exception;

    public Person findPersonById(int id) throws Exception;

    public Admin findAdminById(int id) throws Exception;

    public void removePerson(Person person) throws Exception;

    public void updatePerson(Person person) throws Exception;

    public List<Person> getAllPersons() throws Exception;

    public List<Admin> getAllAdmins() throws Exception;
}
