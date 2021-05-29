package example.spring.jsp.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import example.spring.jsp.model.User;

/**
 * Validate the user data
 */
@Component
public class UserValidator {

    private Pattern regexPattern;

    public UserValidator(String regex) {
        regexPattern = Pattern.compile(regex);
    }

    /**
     * Validate the user's details. Any errors
     * are added to the binding result.
     * 
     * @param user
     * @param bindingResult
     */
    public void validate(User user, BindingResult bindingResult) {
        validate("firstName", user.getFirstName(), "First name", bindingResult);
        validate("lastName", user.getLastName(), "Last name", bindingResult);
        validate("street", user.getStreet(), "Street", bindingResult);
        validate("city", user.getCity(), "City", bindingResult);
        validate("postcode", user.getPostcode(), "Postcode", bindingResult);

        if(user.getHouseNumber() == null) {
            bindingResult.rejectValue("houseNumber", "mandatory.housenumber", "House number must be provided");
        }
    }

    /**
     * Validate the field contains valid characters only.
     * Errors are added to the binding result.
     * 
     * @param lastName
     * @param bindingResult
     */
    private void validate(String fieldName, String value, String text, BindingResult bindingResult) {
        if(value == null || value.isEmpty()) {
            bindingResult.rejectValue(fieldName, "mandatory." + fieldName.toLowerCase(), text + " must be provided");
            return;
        }

        if(!regexPattern.matcher(value).matches()) {
            bindingResult.rejectValue(fieldName, "invalid." + fieldName.toLowerCase(), "Invalid characters in " + text);
        }
    }
}
