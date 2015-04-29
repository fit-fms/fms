package fms.controller;

import fms.business.fieldtype.DateField;
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
        map.addAttribute("date", date  );

        DateField df = new DateField();
        map.addAttribute("field", df  );


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

}
