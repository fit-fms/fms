package fms.business.fieldtype;


/**
 * Nestrukturovan� text, kter� se bere jako celek. Napr�klad "Jm�no" a "Prijmen�" by byla dve samostatn� pol�cka typu text.
 *
 * @author jinora
 * @version 1.0
 * @created 15-Apr-2015 12:39:49 PM
 */
public class TextField extends FieldType {

    public TextField() {

    }


    /**
     * @param data
     */
    public String getText(String data) {
        return data.trim();
    }

}