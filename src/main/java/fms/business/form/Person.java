package fms.business.form;


import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Osoba aktu�ln� uchov�vaj�c� formul�r v pap�rov� forme.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
@JcrNode(classNameProperty = "className")
public class Person {

    @JcrName
    private String jcrName = "fms_person";

    @JcrPath
    private String jcrPath;

    @JcrProperty
    private int id;
    /**
     * Adresa bydl�te osoby
     */
    @JcrProperty
    private String address;
    /**
     * Krestn� jm�no osoby
     */
    @JcrProperty
    private String firstName;
    /**
     * Prijmen� osoby
     */
    @JcrProperty
    private String lastName;
    /**
     * Telefonn� c�slo
     */
    @JcrProperty
    private String phone;
    /**
     * Formul�re kter� osoba uchov�v�.
     */
//    @JcrReference
    private Map<Long, PaperForm> forms;

    public Person() {
        forms = new HashMap<Long, PaperForm>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Z�sk� v�echny pap�rov� formul�re kter� osoba uchov�v�.
     */
    public Map<Long, PaperForm> getPaperForms() {
        return forms;
    }

    /**
     * Prida formular ktery osoba uchovava.
     *
     * @param form
     */
    public void addPaperForm(PaperForm form) {
        form.setPerson(this);
        forms.put(form.getId(), form);
    }

    /**
     * Odstrani formular ktery osoba uchovava
     *
     * @param form
     */
    public void removePaperForm(PaperForm form) {
        PaperForm form1 = forms.remove(form.getId());

        //Form nebyl v invenatri uzivatele
        if (form1 == null) return;

        form.setPerson(null);
    }

    /**
     * Z�sk� adresu bydl�te osoby.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Nastav� adresu bydl�te osobu.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Z�sk� krestn� jm�no osoby.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Nastav� krestn� jm�no osoby.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Z�sk� prijmen� osoby.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Nastav� prijmen� osoby.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Z�sk� telefonn� c�slo.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Nastav� telefonn� c�slo.
     *
     * @param newVal
     */
    public void setPhone(String newVal) {
        phone = newVal;
    }

}