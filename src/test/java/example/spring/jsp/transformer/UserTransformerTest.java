package example.spring.jsp.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import example.spring.jsp.dao.UserEntity;
import example.spring.jsp.model.User;

public class UserTransformerTest {

    private static final long ID = 1;
    private static final String FIRST_NAME = "First name";
    private static final String LAST_NAME = "Last name";
    private static final Long HOUSE_NUMBER = 1L;
    private static final String STREET = "Street";
    private static final String CITY = "City";
    private static final String POSTCODE = "Postcode";

    private UserTransformer transformer;

    @BeforeEach
    public void setUp() {
        transformer = new UserTransformer();
    }

    @DisplayName("Transform data from web model into data store model")
    @Test
    public void testTransformToDataStoreModel() {
       UserEntity entity = transformer.transform(createUser());
       verifyEntity(entity);
    }

    @DisplayName("Transform data from data store model into web model")
    @Test
    public void testTransformToWebModel() {
       User user = transformer.transform(createEntity());
       verifyUser(user);
    }

    @DisplayName("Transform list of data from data store model into web model")
    @Test
    public void testTransformListToWebModel() {
        List<UserEntity> entities = new ArrayList<>();
        entities.add(createEntity());

        List<User> users = transformer.transform(entities);
        assertEquals(1, users.size());
        verifyUser(users.get(0));
    }

    public UserEntity createEntity() {
        UserEntity entity = new UserEntity();
        entity.setId(ID);
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setHouseNumber(HOUSE_NUMBER);
        entity.setStreet(STREET);
        entity.setCity(CITY);
        entity.setPostcode(POSTCODE);

        return entity;
    }

    public User createUser() {
    	User user = new User();
    	user.setId(ID);
    	user.setFirstName(FIRST_NAME);
    	user.setLastName(LAST_NAME);
    	user.setHouseNumber(HOUSE_NUMBER);
    	user.setStreet(STREET);
        user.setCity(CITY);
        user.setPostcode(POSTCODE);

        return user;
    }

    private void verifyEntity(UserEntity entity) {
        assertEquals(ID, entity.getId());
        assertEquals(FIRST_NAME, entity.getFirstName());
        assertEquals(LAST_NAME, entity.getLastName());
        assertEquals(HOUSE_NUMBER, entity.getHouseNumber());
        assertEquals(STREET, entity.getStreet());
        assertEquals(CITY, entity.getCity());
        assertEquals(POSTCODE, entity.getPostcode());
    }

    private void verifyUser(User user) {
        assertEquals(ID, user.getId());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(HOUSE_NUMBER, user.getHouseNumber());
        assertEquals(STREET, user.getStreet());
        assertEquals(CITY, user.getCity());
        assertEquals(POSTCODE, user.getPostcode());
    }
}
