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
        clientList.addClient(42, null);
        assertTrue(clientList.containsClient(42));
        assertFalse(clientList.containsClient(31));
        assertTrue(clientList.containsClient(42));
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
        Socket socket = new Socket();
        Socket socket2 = new Socket();
        clientList.addClient(42, socket);
        assertEquals(socket, clientList.getClient(42));
        assertNotEquals(socket2, clientList.getClient(42));
        assertNull(clientList.getClient(14));
    }

    @Test
    public void testRemoveClient() throws IOException {
        ClientList clientList = new ClientList();
        Socket socket = new Socket();
        clientList.addClient(42, socket);
        assertTrue(clientList.containsClient(42));
        socket.close();
        clientList.getAllClients();
        assertFalse(clientList.containsClient(42));
    }
}
