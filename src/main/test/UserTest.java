import com.example.dinnerreserver.model.User;

import org.junit.jupiter.api.*;
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
    public void testGetEmail(){
        assertEquals("john.doe@example.com", user.getEmail());
    }
    @Test
    public void testSetEmail(){
        user.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", user.getEmail());
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
