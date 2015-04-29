package fms.controller;

import fms.business.fieldtype.DateField;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by smolijar on 4/28/15.
 */

@Controller
public class ViewTestController {

    @RequestMapping(value = "/test/field", method = RequestMethod.GET)
    public String ViewTest(ModelMap map){

       Date date = new Date();
        map.addAttribute("date", date  );

        DateField df = new DateField();
        map.addAttribute("field", df  );


        return "test/field";
    }

}
