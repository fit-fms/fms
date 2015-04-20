package fms.business.archetype;


import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

/**
 * �ablona obsahuje informace pro zobrazen� ci tisk archetypu formul�re.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
@JcrNode
public class Template {

    /**
     * N�zev �ablony
     */
    @JcrName
    private String name;

    @JcrPath
    private String jcrPath;
    
    /**
     * Popis �ablony
     */
    @JcrProperty
    private String description;

    public Template() {

    }


    /**
     * N�zev �ablony
     */
    public String getName() {
        return name;
    }

    /**
     * N�zev �ablony
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Popis �ablony
     */
    public String getDescription() {
        return description;
    }

    /**
     * Popis �ablony
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}