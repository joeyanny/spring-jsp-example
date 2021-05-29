package example.spring.jsp.validator;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import example.spring.jsp.model.User;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    private static final String REGEX = "[A-Za-z-\\s]+";

    private static final String VALID_FIRST_NAME = "First-Name";
    private static final String VALID_LAST_NAME = "Last name";
    private static final long VALID_HOUSE_NUMBER = 1L;
    private static final String VALID_STREET = "Street";
    private static final String VALID_CITY = "City";
    private static final String VALID_POSTCODE = "Postcode";

    private static final String INVALID_FIRST_NAME = "Invalid last name!";
    private static final String INVALID_LAST_NAME = "Name*";
    private static final String INVALID_STREET = "^Street";
    private static final String INVALID_CITY = "City$$$";
    private static final String INVALID_POSTCODE = "P0stC0d3";

    private static final String FIRST_NAME_FIELD = "firstName";
    private static final String LAST_NAME_FIELD = "lastName";
    private static final String HOUSE_NUMBER_FIELD = "houseNumber";
    private static final String STREET_FIELD = "street";
    private static final String CITY_FIELD = "city";
    private static final String POSTCODE_FIELD = "postcode";

    private static final String MANDATORY_ERROR_PREFIX = "mandatory.";
    private static final String INVALID_ERROR_PREFIX = "invalid.";

    @Mock
    private BindingResult bindingResult;

    private UserValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new UserValidator(REGEX);
    }

    @DisplayName("Name is valid")
    @ParameterizedTest
    @ValueSource(strings = {"Name", "Multiple-Names", "Multiple Names"})
    public void testNameValid(String name) {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(anyString(), anyString(), anyString());
    }

    @DisplayName("First name is missing")
    @Test
    public void testFirstNameMissing() {
        User user = new User();
        user.setFirstName(null);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult).rejectValue(eq(FIRST_NAME_FIELD), eq(MANDATORY_ERROR_PREFIX + FIRST_NAME_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("First name is empty")
    @Test
    public void testFirstNameEmpty() {
        User user = new User();
        user.setFirstName("");
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult).rejectValue(eq(FIRST_NAME_FIELD), eq(MANDATORY_ERROR_PREFIX + FIRST_NAME_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Last name is missing")
    @Test
    public void testLastNameMissing() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(null);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(LAST_NAME_FIELD), eq(MANDATORY_ERROR_PREFIX + LAST_NAME_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Last name is empty")
    @Test
    public void testLastNameEmpty() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName("");
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(LAST_NAME_FIELD), eq(MANDATORY_ERROR_PREFIX + LAST_NAME_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("House number is missing")
    @Test
    public void testHouseNumberMissing() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(null);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(HOUSE_NUMBER_FIELD), eq(MANDATORY_ERROR_PREFIX + HOUSE_NUMBER_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Street is missing")
    @Test
    public void testStreetMissing() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(null);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(STREET_FIELD), eq(MANDATORY_ERROR_PREFIX + STREET_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Street is empty")
    @Test
    public void testStreetEmpty() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet("");
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(STREET_FIELD), eq(MANDATORY_ERROR_PREFIX + STREET_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("City is missing")
    @Test
    public void testCityMissing() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(null);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(CITY_FIELD), eq(MANDATORY_ERROR_PREFIX + CITY_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("City is empty")
    @Test
    public void testCityEmpty() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity("");
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(CITY_FIELD), eq(MANDATORY_ERROR_PREFIX + CITY_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Postcode is missing")
    @Test
    public void testPostcodeMissing() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(null);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(POSTCODE_FIELD), eq(MANDATORY_ERROR_PREFIX + POSTCODE_FIELD), anyString());
    }

    @DisplayName("Postcode is empty")
    @Test
    public void tesPostcodeEmpty() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode("");

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(POSTCODE_FIELD), eq(MANDATORY_ERROR_PREFIX + POSTCODE_FIELD), anyString());
    }

    @DisplayName("All fields missing")
    @Test
    public void testAllFieldsMissing() {
        User user = new User();

        validator.validate(user, bindingResult);

        verify(bindingResult).rejectValue(eq(FIRST_NAME_FIELD), eq(MANDATORY_ERROR_PREFIX + FIRST_NAME_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(LAST_NAME_FIELD), eq(MANDATORY_ERROR_PREFIX + LAST_NAME_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(HOUSE_NUMBER_FIELD), eq(MANDATORY_ERROR_PREFIX + HOUSE_NUMBER_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(STREET_FIELD), eq(MANDATORY_ERROR_PREFIX + STREET_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(CITY_FIELD), eq(MANDATORY_ERROR_PREFIX + CITY_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(POSTCODE_FIELD), eq(MANDATORY_ERROR_PREFIX + POSTCODE_FIELD), anyString());
    }

    @DisplayName("First name is invalid")
    @Test
    public void testFirstNameInvalid() {
        User user = new User();
        user.setFirstName(INVALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult).rejectValue(eq(FIRST_NAME_FIELD), eq(INVALID_ERROR_PREFIX + FIRST_NAME_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Last name is invalid")
    @Test
    public void testLastNameInvalid() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(INVALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(LAST_NAME_FIELD), eq(INVALID_ERROR_PREFIX + LAST_NAME_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Street is invalid")
    @Test
    public void testStreetInvalid() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(INVALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(STREET_FIELD), eq(INVALID_ERROR_PREFIX + STREET_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("City is invalid")
    @Test
    public void testCityInvalid() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(INVALID_CITY);
        user.setPostcode(VALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(CITY_FIELD), eq(INVALID_ERROR_PREFIX + CITY_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(POSTCODE_FIELD), anyString(), anyString());
    }

    @DisplayName("Postcode is invalid")
    @Test
    public void testPostcodeInvalid() {
        User user = new User();
        user.setFirstName(VALID_FIRST_NAME);
        user.setLastName(VALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(VALID_STREET);
        user.setCity(VALID_CITY);
        user.setPostcode(INVALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult, never()).rejectValue(eq(FIRST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(LAST_NAME_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(STREET_FIELD), anyString(), anyString());
        verify(bindingResult, never()).rejectValue(eq(CITY_FIELD), anyString(), anyString());
        verify(bindingResult).rejectValue(eq(POSTCODE_FIELD), eq(INVALID_ERROR_PREFIX + POSTCODE_FIELD), anyString());
    }

    @DisplayName("All fields invalid")
    @Test
    public void testAllFieldsInvalid() {
        User user = new User();
        user.setFirstName(INVALID_FIRST_NAME);
        user.setLastName(INVALID_LAST_NAME);
        user.setHouseNumber(VALID_HOUSE_NUMBER);
        user.setStreet(INVALID_STREET);
        user.setCity(INVALID_CITY);
        user.setPostcode(INVALID_POSTCODE);

        validator.validate(user, bindingResult);

        verify(bindingResult).rejectValue(eq(FIRST_NAME_FIELD), eq(INVALID_ERROR_PREFIX + FIRST_NAME_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(LAST_NAME_FIELD), eq(INVALID_ERROR_PREFIX + LAST_NAME_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(STREET_FIELD), eq(INVALID_ERROR_PREFIX + STREET_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(CITY_FIELD), eq(INVALID_ERROR_PREFIX + CITY_FIELD), anyString());
        verify(bindingResult).rejectValue(eq(POSTCODE_FIELD), eq(INVALID_ERROR_PREFIX + POSTCODE_FIELD), anyString());
        verify(bindingResult, never()).rejectValue(eq(HOUSE_NUMBER_FIELD), anyString(), anyString());
    }
}
