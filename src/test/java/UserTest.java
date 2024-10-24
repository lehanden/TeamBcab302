import com.example.dinnerreserver.model.MockUserDAO;
import com.example.dinnerreserver.model.User;
import com.example.dinnerreserver.model.SqliteUserDAO;
import com.example.dinnerreserver.controller.MainController;

import org.junit.jupiter.api.*;

import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    @BeforeEach
    public void setup(){
        user = new User("john","john.doe@example.com", "password123");
    }

    @Test
    public void testGetUserID(){
        user.setId(1);
        assertEquals(1,user.getId());
    }

    @Test
    public void testGetUsername(){
        assertEquals("john", user.getUsername());
    }
    @Test
    public void testSetUsername(){
        user.setUsername("jane");
        assertEquals("jane", user.getUsername());
    }
    @Test
    public void testDuplicateUsername() {
        MockUserDAO userDAO = new MockUserDAO();
        User firstUser = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(firstUser);
        User duplicateUser = new User("john", "jane.doe@example.com", "password321");
        assertNotNull(userDAO.getUserByUsername("john"));
        User existingUser = userDAO.getUserByUsername(duplicateUser.getUsername());
        assertNotNull(existingUser, "Username already exists!");
    }

    @Test
    public void testGetEmail(){
        assertEquals("john.doe@example.com", user.getEmail());
    }
    @Test
    public void testSetEmail(){
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
    }
    @Test
    public void testValidEmail() {
        assertTrue(User.isValidEmail("john.doe@example.com"));
        assertTrue(User.isValidEmail("jane_doe123@example.co.uk"));
        assertTrue(User.isValidEmail("user+mailbox@domain.com"));
    }
    @Test
    public void testInvalidEmail() {
        assertFalse(User.isValidEmail("john.doeexample.com"));
        assertFalse(User.isValidEmail("john.doe@.com"));
        assertFalse(User.isValidEmail("john.doe@example"));
        assertFalse(User.isValidEmail("john"));
    }
    @Test
    public void testDuplicateEmail() {
        MockUserDAO userDAO = new MockUserDAO();
        User firstUser = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(firstUser);
        User duplicateUser = new User("jane", "john.doe@example.com", "password321");
        assertNotNull(userDAO.getUserByEmail("john.doe@example.com"));
        User existingUser = userDAO.getUserByEmail(duplicateUser.getEmail());
        assertNotNull(existingUser, "Email already in use!");
    }

    @Test
    public void testGetPassword(){
        assertEquals("password123", user.getPassword());
    }
    @Test
    public void testSetPassword(){
        user.setPassword("password321");
        assertEquals("password321", user.getPassword());
    }
}
class SqliteUserDAOTest {

    private SqliteUserDAO userDAO;

    @BeforeEach
    public void setUp() {
        userDAO = new SqliteUserDAO();
        clearUsersTable();  // Clear the table before each test
    }

    private void clearUsersTable() {
        try {
            Statement statement = userDAO.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM users");
            // Removed VACUUM since it's causing issues
            // statement.executeUpdate("VACUUM");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() {
        User user = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(user);

        User retrievedUser = userDAO.getUserByUsername("john");
        assertNotNull(retrievedUser);
        assertEquals("john.doe@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        User user = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(user);

        User retrievedUser = userDAO.getUser(user.getId());
        assertNotNull(retrievedUser);
        assertEquals("john", retrievedUser.getUsername());
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(user);

        User retrievedUser = userDAO.getUserByUsername("john");
        assertNotNull(retrievedUser);
        assertEquals("john.doe@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(user);

        User retrievedUser = userDAO.getUserByEmail("john.doe@example.com");
        assertNotNull(retrievedUser);
        assertEquals("john", retrievedUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        User user = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(user);

        // Update user details
        user.setUsername("johnny");
        user.setEmail("johnny.doe@example.com");
        user.setPassword("newpassword123");
        userDAO.updateUser(user);

        User updatedUser = userDAO.getUser(user.getId());
        assertEquals("johnny", updatedUser.getUsername());
        assertEquals("johnny.doe@example.com", updatedUser.getEmail());
        assertEquals("newpassword123", updatedUser.getPassword());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("john", "john.doe@example.com", "password123");
        userDAO.addUser(user);

        userDAO.deleteUser(user);
        User deletedUser = userDAO.getUser(user.getId());
        assertNull(deletedUser);
    }

    @Test
    public void testGetAllUsers() {
        // Clear the table to avoid interference
        clearUsersTable();

        User user1 = new User("john", "john.doe@example.com", "password123");
        User user2 = new User("jane", "jane.doe@example.com", "password123");
        userDAO.addUser(user1);
        userDAO.addUser(user2);

        List<User> users = userDAO.getAllUsers();
        assertEquals(2, users.size());
    }
}


