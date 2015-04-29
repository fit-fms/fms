package fms.controller;

import fms.business.archetype.Archetype;
import fms.business.archetype.Field;
import fms.business.archetype.PublishedArchetype;
import fms.business.fieldtype.DateField;
import fms.business.fieldtype.FieldType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by smolijar on 4/28/15.
 */

@Controller
public class ViewTestController {

    @RequestMapping(value = "/test/field", method = RequestMethod.GET)
    public String FieldTypeTest(ModelMap map){

       Date date = new Date();
        map.addAttribute("date", date);

        FieldType df = new DateField();
        Field f = new Field();
        df.setName("DATEFIELDNAME");
        df.setDescription("DF descr");
        f.setType(df);
        f.setLabel("fieldlabel");
        f.setName("fieldname");
        f.setPrivateDescription("prvdesc");
        f.setPublicDescription("pubdesc");
        map.addAttribute("field", f  );


        return "test/field";
    }

    @RequestMapping(value = "/test/errors", method = RequestMethod.GET)
    public String ErrorsTest(ModelMap map){


        ArrayList<String> errors = new ArrayList<String>();
        for( int i = 0; i < 10; i++ )
            errors.add("Error NO."+i);

        map.addAttribute("errors", errors  );

        return "errors";
    }

    @RequestMapping(value = "/test/fillOut", method = RequestMethod.GET)
    public String fillOutTest(ModelMap map){


        Archetype arch = new PublishedArchetype();
        FieldType ft = new DateField();
        ft.setName("FieldTypeName");

        Field rf = new Field();
        rf.setType(ft);
        rf.setName("rf");
        rf.setLabel("rflab");

        Field of = new Field();
        of.setType(ft);
        of.setName("of");
        of.setLabel("ofl");

        arch.addRequiredField(rf);
        arch.addOptionalField(of);
        arch.setName("archName");

        map.addAttribute("archetype", arch );

        return "fillOutForm";
    }

}
