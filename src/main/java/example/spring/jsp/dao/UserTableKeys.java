package example.spring.jsp.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Name of fields within the user table
 */
public class UserTableKeys {

    public static final String TABLE_NAME = "user";
    public static final String ID = "user_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String HOUSE_NUMBER = "house_number";
    public static final String STREET = "street";
    public static final String CITY = "city";
    public static final String POSTCODE = "postcode";

    /**
     * Get a list of table field names
     * 
     * @return keys
     */
    public static List<String> getKeys() {
        List<String> keyList = new ArrayList<String>();
        keyList.add(FIRST_NAME);
        keyList.add(LAST_NAME);
        keyList.add(HOUSE_NUMBER);
        keyList.add(STREET);
        keyList.add(CITY);
        keyList.add(POSTCODE);

        return keyList;
    }
}
