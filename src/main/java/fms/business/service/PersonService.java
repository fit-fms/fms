package fms.business.service;

import fms.business.form.Admin;
import fms.business.form.Person;

import java.util.List;

/**
 * Sluzba pro ukladani Osob
 */
public interface PersonService {

    /**
     * Vytvori novou Osobu
     * @param person osoba k ulozeni
     * @throws Exception
     */
    public void createPerson(Person person) throws Exception;

    /**
     * Najde osobu podle id
     * @param id id osoby
     * @return
     * @throws Exception
     */
    public Person findPersonById(int id) throws Exception;

    /**
     * Najde admina podle id
     * @param id admina
     * @return
     * @throws Exception
     */
    public Admin findAdminById(int id) throws Exception;

    /**
     * Odstrani osobu
     * @param person osoba k odstraneni
     * @throws Exception
     */
    public void removePerson(Person person) throws Exception;

    /**
     * Ulozi zmeny osoby
     * @param person osoba k ulozeni
     * @throws Exception
     */
    public void updatePerson(Person person) throws Exception;

    /**
     * Ziska seznam vsech osob. (Vcetne adminu)
     * @return vsechny osoby
     * @throws Exception
     */
    public List<Person> getAllPersons() throws Exception;

    /**
     * Ziska seznam vsech adminu
     * @return vsichni admini
     * @throws Exception
     */
    public List<Admin> getAllAdmins() throws Exception;
}
