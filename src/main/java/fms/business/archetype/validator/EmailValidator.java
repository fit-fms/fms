package fms.business.archetype.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator extends Validator {

    //Source: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    private Pattern pattern;

    public EmailValidator() {
        super();
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public boolean validate(String data, List<String> errors) {
        Matcher matcher = pattern.matcher(data);

        boolean status = matcher.matches();

        if (!status && errors != null) {
            errors.add("Prosim zadejte platny email!");
        }

        return status;
    }
}
