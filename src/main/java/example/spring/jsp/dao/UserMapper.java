package example.spring.jsp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import example.spring.jsp.model.User;

/**
 * Map data from the DB for a user
 */
public class UserMapper implements RowMapper<User> {

    /**
     * Map the data from the database to the user object
     */
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(UserTableKeys.ID));
        user.setFirstName(resultSet.getString(UserTableKeys.FIRST_NAME));
        user.setLastName(resultSet.getString(UserTableKeys.LAST_NAME));
        user.setHouseNumber(resultSet.getString(UserTableKeys.HOUSE_NUMBER));
        user.setStreet(resultSet.getString(UserTableKeys.STREET));
        user.setCity(resultSet.getString(UserTableKeys.CITY));
        user.setPostcode(resultSet.getString(UserTableKeys.POSTCODE));

        return user;
    }
}
