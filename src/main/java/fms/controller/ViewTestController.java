package fms.controller;

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

        String string = "08/16/2011";
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.addAttribute("date", date  );

        return "test/field";
    }

}
