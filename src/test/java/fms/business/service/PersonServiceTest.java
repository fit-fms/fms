package fms.business.service;

import fms.business.form.Admin;
import fms.business.form.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;


public class PersonServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        resetWorkspace();
    }

    @Autowired
    private PersonService personService;

    @Test
    public void testCreate() throws Exception {
        int id = 654;
        String firstName = "First Name";
        String lastName = "Last Name";
        String address = "AA BB CC";
        String phone = "+333 444 555 666";

        Person person = new Admin();
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setPhone(phone);

        personService.createPerson(person);

        Person personA = personService.findPersonById(id);
        assertNotNull(personA);
        assertTrue(personA instanceof Admin);
        assertEquals(id, personA.getId());
        assertEquals(firstName, personA.getFirstName());
        assertEquals(lastName, personA.getLastName());
        assertEquals(address, personA.getAddress());
        assertEquals(phone, personA.getPhone());
    }

    @Test
    public void testRemove() throws Exception {
        int id = 7453;

        Person person = new Person();
        person.setId(id);

        personService.createPerson(person);
        personService.removePerson(person);

        Person personA = personService.findPersonById(id);
        assertNull(personA);
    }

    @Test
    public void testUpdate() throws Exception {
        int id = 852;
        int newId = id + 332;

        Person person = new Admin();
        person.setId(id);

        personService.createPerson(person);
        person.setId(newId);
        personService.updatePerson(person);

        Person personA = personService.findPersonById(newId);
        assertNotNull(personA);
        assertEquals(newId, personA.getId());

        Person personNo = personService.findAdminById(id);
        assertNull(personNo);
    }

    @Test
    public void testFindAdmins() throws Exception {

        int numOfPersons = 2;
        int idA = 1;
        int idB = 2;

        int numOfAdmins = 1;
        int idC = 3;

        Person pA = new Person();
        pA.setId(idA);

        Person pB = new Person();
        pB.setId(idB);

        Admin pC = new Admin();
        pC.setId(idC);

        personService.createPerson(pA);
        personService.createPerson(pB);
        personService.createPerson(pC);


        List<Person> allPersons = personService.getAllPersons();
        assertNotNull(allPersons);
        assertEquals(numOfPersons + numOfAdmins, allPersons.size());

        List<Admin> admins = personService.getAllAdmins();
        assertNotNull(admins);
        assertEquals(numOfAdmins, admins.size());

    }
}