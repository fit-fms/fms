package fms.business.service;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;

import java.util.List;
import java.util.Map;

/**
 * Filtruje formulare podle nasledujicich podminek:  Formulare pouze urciteho Archetypu - vyhledava se ve vsech zadanych(spojeni OR). Nebo ve vsech existujich archetypech.  Policka - vyhledaji se formulare splnujici vsechny podminky(AND).  Razeni - orderBy se aplikuji ve stejnem poradi jako byly volany.  Offset  a limit se aplikuji jako posledni
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class SearchCondition {

    /**
     * Seznam arhetypu mezi jejich� fomul�ru se m� vyhled�vat.
     */
    private List<Archetype> archetypes;

    /**
     * Seznam mo�n�ch hodnot. <NazevPolcka, HodnotaKteraSeHleda>
     */
    private Map<Field, String> fieldValues;
    /**
     * Maximalni pocet formul�ru kter� m�me vr�tit.
     */
    private int limit;
    /**
     * Porad� v�sledku.
     */
    private Map<Field, Order> order;
    /**
     * Odsazen� v�sledku kter� se maj� vr�tit.
     */
    private int offset;

    public SearchCondition() {

    }

    /**
     * @param archetypes archetypes
     */
    public SearchCondition addArchetypes(Archetype[] archetypes) {
        return this;
    }

    /**
     * @param archetype archetype
     */
    public SearchCondition addArchetype(Archetype archetype) {
        return this;
    }

    /**
     * @param field field
     * @param value
     */
    public SearchCondition addField(Field field, String value) {
        return this;
    }

    /**
     * @param fields
     * @param values values
     */
    public SearchCondition addFields(Field[] fields, String[] values) {
        return this;
    }

    /**
     * @param Field
     * @param order ASC / DESC - Konstanty SearchCondition
     */
    public SearchCondition orderBy(Field Field, Order order) {
        return this;
    }

    public int getLimit() {
        return limit;
    }

    /**
     * @param limit limit
     */
    public SearchCondition setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    /**
     * @param offset offset
     */
    public SearchCondition setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public Map<Field, Order> getOrder() {
        return order;
    }

    public Map<Field, String> getFields() {
        return fieldValues;
    }

    public List<Archetype> getArchetypes() {
        return archetypes;
    }

    public enum Order {
        /**
         * Vzestupn� porad�.
         */
        ASC,
        /**
         * Sestupn� porad�.
         */
        DESC
    }
}