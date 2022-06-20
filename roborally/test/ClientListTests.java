import bb.roborally.game.User;
import bb.roborally.server.ClientList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class ClientListTests {

    @Test
    public void containsClientTest() {
        ClientList clientList = new ClientList();
        User user1 = new User(42);
        User user2 = new User(13);
        User user3 = new User(42);
        clientList.addClient(user1, null);
        assertTrue(clientList.containsClient(user1));
        assertFalse(clientList.containsClient(user2));
        assertTrue(clientList.containsClient(user3));
    }

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
    public void testGetClientSocket() {
        ClientList clientList = new ClientList();
        User user = new User(42);
        User user1 = new User(14);
        Socket socket = new Socket();
        Socket socket2 = new Socket();
        clientList.addClient(user, socket);
        assertEquals(socket, clientList.getClientSocket(user));
        assertNotEquals(socket2, clientList.getClientSocket(user));
        assertNull(clientList.getClientSocket(user1));
    }

    @Test
    public void testRemoveClient() throws IOException {
        ClientList clientList = new ClientList();
        User user = new User(42);
        Socket socket = new Socket();
        clientList.addClient(user, socket);
        assertTrue(clientList.containsClient(user));
        clientList.removeClient(user);
        assertFalse(clientList.containsClient(user));
    }
}
