package fms.business.archetype;


import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

/**
 * �ablona obsahuje informace pro zobrazen� ci tisk archetypu formul�re.
 */
@JcrNode(classNameProperty = "className")
public class Template {

    /**
     * N�zev �ablony
     */
    @JcrName
    private String jcrName = "fms_template";

    @JcrPath
    private String jcrPath;

    @JcrProperty
    private String name;
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