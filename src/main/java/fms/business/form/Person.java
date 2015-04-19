package fms.business.form;


import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrProperty;
import org.jcrom.annotations.JcrReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Osoba aktu�ln� uchov�vaj�c� formul�r v pap�rov� forme.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
@JcrNode
public class Person {

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
    @JcrReference
    private Map<Integer, PaperForm> forms;

    public Person() {
        forms = new HashMap<Integer, PaperForm>();
    }


    /**
     * Z�sk� v�echny pap�rov� formul�re kter� osoba uchov�v�.
     */
    public Map<Integer, PaperForm> getPaperForms() {
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