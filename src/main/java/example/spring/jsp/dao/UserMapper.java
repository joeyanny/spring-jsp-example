package example.spring.jsp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Map data from the DB for a user
 */
public class UserMapper implements RowMapper<UserEntity> {

    /**
     * Map the data from the database to the user object
     */
    public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    	UserEntity user = new UserEntity();
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
