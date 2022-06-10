import bb.roborally.data.messages.*;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.connection.HelloClient;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.data.util.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class TypeAdapterTests {

    @Test
    public void testChatMessageSerialization() throws IOException {
        ChatMessage chatMessage = new ChatMessage(new User("Alice"), "Hello, World!");
        String json = chatMessage.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CHAT_MESSAGE, envelopeParsed.getMessageType());
        ChatMessage chatMessageParsed = (ChatMessage) envelopeParsed.getMessageBody();
        assertEquals(chatMessage.getSender(), chatMessageParsed.getSender());
        assertEquals(chatMessage.getMessage(), chatMessageParsed.getMessage());
    }

    @Test
    public void testLoginConfirmationSerialization() throws IOException {
        LoginConfirmation loginConfirmation = new LoginConfirmation(new User("alice"));
        String json = loginConfirmation.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.LOGIN_CONFIRMATION, envelopeParsed.getMessageType());
        LoginConfirmation loginConfirmationParsed = (LoginConfirmation) envelopeParsed.getMessageBody();
        assertEquals(loginConfirmation.getUser(), loginConfirmationParsed.getUser());
    }

    @Test
    public void testLoginErrorSerialization() throws IOException {
        LoginError loginError = new LoginError(new User("alice"), "The username is already taken!");
        String json = loginError.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.LOGIN_ERROR, envelopeParsed.getMessageType());
        LoginError loginErrorParsed = (LoginError) envelopeParsed.getMessageBody();
        assertEquals(loginError.getUser(), loginErrorParsed.getUser());
        assertEquals(loginError.getMessage(), loginErrorParsed.getMessage());
    }

    @Test
    public void testLoginRequestSerialization() throws IOException {
        LoginRequest loginRequest = new LoginRequest(new User("alice"));
        String json = loginRequest.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.LOGIN_REQUEST, envelopeParsed.getMessageType());
        LoginRequest loginRequestParsed = (LoginRequest) envelopeParsed.getMessageBody();
        assertEquals(loginRequest.getUser(), loginRequestParsed.getUser());
    }

    @Test
    public void testLogoutConfirmationSerialization() throws IOException {
        LogoutConfirmation logoutConfirmation = new LogoutConfirmation(new User("alice"));
        String json = logoutConfirmation.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.LOGOUT_CONFIRMATION, envelopeParsed.getMessageType());
        LogoutConfirmation logoutConfirmationParsed = (LogoutConfirmation) envelopeParsed.getMessageBody();
        assertEquals(logoutConfirmation.getUser(), logoutConfirmationParsed.getUser());
    }

    @Test
    public void testLogoutRequestSerialization() throws IOException {
        LogoutRequest logoutRequest = new LogoutRequest(new User("alice"));
        String json = logoutRequest.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.LOGOUT_REQUEST, envelopeParsed.getMessageType());
        LogoutRequest logoutRequestParsed = (LogoutRequest) envelopeParsed.getMessageBody();
        assertEquals(logoutRequest.getUser(), logoutRequestParsed.getUser());
    }

    @Test
    public void testHelloClientSerialization() throws IOException{
         HelloClient helloClient = new HelloClient();
         String json = helloClient.toJson();
         Envelope envelopeParsed = Envelope.fromJson(json);
         assertSame(Envelope.MessageType.HELLO_CLIENT, envelopeParsed.getMessageType());
         HelloClient helloClientParsed  = (HelloClient) envelopeParsed.getMessageBody();
         assertEquals(helloClient.getProtocol(), helloClientParsed.getProtocol());

    }

    @Test
    public void testAliveSerialization() throws IOException{
        Alive alive = new Alive();
        String json = alive.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.ALIVE, envelopeParsed.getMessageType());
        assertTrue(envelopeParsed.getMessageBody() instanceof Alive);
    }

    @Test
    public void testHelloServerSerialization() throws IOException{
        HelloServer helloServer = new HelloServer();
        String json = helloServer.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.HELLO_SERVER, envelopeParsed.getMessageType());
        HelloServer helloServerParsed = (HelloServer) envelopeParsed.getMessageBody();
        assertEquals(helloServer.getGroup(), helloServerParsed.getGroup());
        assertEquals(helloServer.isAI(), helloServerParsed.isAI());
        assertEquals(helloServer.getProtocol(), helloServerParsed.getProtocol());
    }

    @Test
    public void testWelcomeSerialization() throws IOException{
        Welcome welcome = new Welcome();
        String json = welcome.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.WELCOME, envelopeParsed.getMessageType());
        Welcome welcomeParsed = (Welcome) envelopeParsed.getMessageBody();
        assertEquals(welcome.getClientID(), welcomeParsed.getClientID());
    }

}
