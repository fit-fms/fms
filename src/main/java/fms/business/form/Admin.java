package fms.business.form;


import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

/**
 * Clen organizace zodpovedn� za spr�vu formul�ru.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:47 PM
 */
@JcrNode
public class Admin {

    @JcrName
    private String jcrName = "admin";

    @JcrPath
    private String jcrPath;

    /**
     * Krestn� jm�no spr�vce
     */
    @JcrProperty
    private String firstName;

    /**
     * Prijmen� spr�vce
     */
    @JcrProperty
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