package example.spring.jsp.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

/**
 * Data access object for integrating with the data source
 * for storing and retrieving user data.
 */
public class UserDAO {

    private static String GET_LIST = "SELECT * FROM %s";
    private static String GET_ITEM = "SELECT * FROM %s WHERE %s = :%s";
    private static String SAVE_ITEM = "INSERT INTO %s (%s) VALUES (%s)";
    private static String UPDATE_ITEM = "UPDATE %s SET %s WHERE %s = :%s";
    private static String DELETE_ITEM = "DELETE FROM %s WHERE %s = :%s";

    /** Template for integrating with JDBC */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Query the data source to retrieve a list of users.
     * 
     * @return users
     * @throws SQLException
     */
    public List<UserEntity> getUserList() throws SQLException {
        String query = String.format(GET_LIST, UserTableKeys.TABLE_NAME);
        return jdbcTemplate.query(query, new UserMapper());
    }

    /**
     * Query the data source to retrieve a user by their ID.
     * 
     * @param id
     * @return user
     * @throws SQLException
     */
    public UserEntity getUser(long id) throws SQLException {
        String query = String.format(GET_ITEM, UserTableKeys.TABLE_NAME, UserTableKeys.ID, UserJdbcParameterKeys.ID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(UserJdbcParameterKeys.ID, id);

        return jdbcTemplate.queryForObject(query, parameters, new UserMapper());
    }

    /**
     * Store a new user in the data source.
     * Note: An ID will be auto-generated for the user.
     * 
     * @param user
     * @throws SQLException
     */
    public void saveUser(UserEntity user) throws SQLException {
        String keysList = StringUtils.arrayToCommaDelimitedString(UserTableKeys.getKeys().toArray());
        String namedParametersList = StringUtils.arrayToCommaDelimitedString(UserJdbcParameterKeys.getQueryFormattedNamedParameterKeys().toArray());

        String query = String.format(SAVE_ITEM, UserTableKeys.TABLE_NAME, keysList, namedParametersList);

        jdbcTemplate.update(query, getNamedParameters(user));
    }

    /**
     * Update an existing user within the data source.
     * Note: This will overwrite every field.
     * 
     * @param id
     * @param user
     * @throws SQLException
     */
    public void updateUser(long id, UserEntity user) throws SQLException {
        List<String> keys = UserTableKeys.getKeys();
        String[] fields = new String[keys.size()];
        for(int i=0; i<keys.size(); i++) {
            fields[i] = keys.get(i) + " = " + UserJdbcParameterKeys.getQueryFormattedNamedParameterKeys().get(i);
        }

        String query = String.format(UPDATE_ITEM, UserTableKeys.TABLE_NAME, StringUtils.arrayToCommaDelimitedString(fields), UserTableKeys.ID, UserJdbcParameterKeys.ID);

        Map<String, Object> parameters = getNamedParameters(user);
        parameters.put(UserJdbcParameterKeys.ID, id);

        jdbcTemplate.update(query, parameters);
    }

    /**
     * Delete a user with a matching ID
     * 
     * @param id
     * @throws SQLException
     */
    public void deleteUser(long id) throws SQLException {
        String query = String.format(DELETE_ITEM, UserTableKeys.TABLE_NAME, UserTableKeys.ID, UserJdbcParameterKeys.ID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(UserJdbcParameterKeys.ID, id);

        jdbcTemplate.update(query, parameters);
    }

    /**
     * Set the data source to connect to for storing and retrieving data
     * 
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Create mappings between user data and table columns to
     * enable use of named parameter queries.
     * 
     * @return parameters
     */
    private Map<String, Object> getNamedParameters(UserEntity user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(UserJdbcParameterKeys.FIRST_NAME, user.getFirstName());
        parameters.put(UserJdbcParameterKeys.LAST_NAME, user.getLastName());
        parameters.put(UserJdbcParameterKeys.HOUSE_NUMBER, user.getHouseNumber());
        parameters.put(UserJdbcParameterKeys.STREET, user.getStreet());
        parameters.put(UserJdbcParameterKeys.CITY, user.getCity());
        parameters.put(UserJdbcParameterKeys.POSTCODE, user.getPostcode());

        return parameters;
    }
}
