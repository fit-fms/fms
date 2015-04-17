package fms.business.form;


/**
 * Clen organizace zodpovedn� za spr�vu formul�ru.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:47 PM
 */
public class Admin {

    /**
     * Krestn� jm�no spr�vce
     */
    private String firstName;
    /**
     * Prijmen� spr�vce
     */
    private String lastName;

    public Admin() {

    }


    /**
     * Krestn� jm�no spr�vce
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Krestn� jm�no spr�vce
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Prijmen� spr�vce
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Prijmen� spr�vce
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}