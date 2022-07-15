import bb.roborally.server.ClientList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ClientListTests {

    @Test
    public void containsClientTest() {
        ClientList clientList = new ClientList();
        clientList.addClient(42, new Socket());
        assertTrue(clientList.containsClient(42));
        assertFalse(clientList.containsClient(31));
        assertTrue(clientList.containsClient(42));
    }

    @Test
    public void testClearClientList() throws IOException {
        ClientList clientList = new ClientList();
        Socket socket = new Socket();
        Socket socket1 = new Socket();
        clientList.addClient(42, socket);
        clientList.addClient(13, socket1);
        assertTrue(clientList.containsClient(42));
        assertTrue(clientList.containsClient(13));
        socket.close();
        clientList.clearClientList();
        assertFalse(clientList.containsClient(42));
        assertTrue(clientList.containsClient(13));
    }

    @Test
    public void testGetClient() {
        ClientList clientList = new ClientList();
        Socket socket = new Socket();
        Socket socket2 = new Socket();
        clientList.addClient(42, socket);
        assertEquals(socket, clientList.getClient(42));
        assertNotEquals(socket2, clientList.getClient(42));
        assertNull(clientList.getClient(14));
    }

    @Test
    public void testGetAllClients() throws IOException {
        ClientList clientList = new ClientList();
        Socket socket = new Socket();
        clientList.addClient(42, socket);
        clientList.addClient(13, socket);
        clientList.addClient(14, socket);
        clientList.addClient(15, socket);
        clientList.addClient(18, socket);
        clientList.addClient(42, socket);
        ArrayList<Socket> sockets = clientList.getAllClients();
        assertEquals(5, sockets.size());
        socket.close();
        sockets = clientList.getAllClients();
        assertEquals(0, sockets.size());
    }

    @Test
    public void testGetAllClientsExcept() throws IOException {
        ClientList clientList = new ClientList();
        Socket socket = new Socket();
        clientList.addClient(42, socket);
        clientList.addClient(13, socket);
        clientList.addClient(14, socket);
        clientList.addClient(15, socket);
        clientList.addClient(18, socket);
        clientList.addClient(42, socket);
        ArrayList<Socket> sockets = clientList.getAllClientsExcept(13);
        assertEquals(4, sockets.size());
        socket.close();
        sockets = clientList.getAllClientsExcept(13);
        assertEquals(0, sockets.size());
    }
}
