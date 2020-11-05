package example.spring.jsp.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Name of parameters used by named parameter JDBC templates
 */
public class UserJdbcParameterKeys {

	public static final String TABLE_NAME = "user";
	public static final String ID = "id";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String HOUSE_NUMBER = "house_number";
	public static final String STREET = "street";
	public static final String CITY = "city";
    public static final String POSTCODE = "postcode";

    /**
     * Get a list of named parameters formatted for use in JDBC queries.
     * 
     * @return namedParameters
     */
    public static List<String> getQueryFormattedNamedParameterKeys() {
		List<String> namedParameters = new ArrayList<String>();
		namedParameters.add(":" + FIRST_NAME);
		namedParameters.add(":" + LAST_NAME);
		namedParameters.add(":" + HOUSE_NUMBER);
		namedParameters.add(":" + STREET);
		namedParameters.add(":" + CITY);
		namedParameters.add(":" + POSTCODE);

		return namedParameters;
	}
}
