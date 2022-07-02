import bb.roborally.game.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
}
