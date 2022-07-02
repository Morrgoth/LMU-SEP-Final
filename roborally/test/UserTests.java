import bb.roborally.game.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {
    @Test
    public void testUserEquals() {
        User user1 = new User(42);
        User user2 = new User(13);
        User user3 = new User(42);
        assertNotEquals(user1, user2);
        assertEquals(user1, user3);
        assertNotEquals(user2, user3);
    }

    @Test
    public void testArrayListContainsUser() {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User(42);
        User user2 = new User(13);
        User user3 = new User(42);
        users.add(user1);
        assertFalse(users.contains(user2));
        users.add(user2);
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));
    }
}
