import bb.roborally.data.messages.*;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.connection.HelloClient;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.data.messages.gameplay.CardPlayed;
import bb.roborally.data.messages.gameplay.CurrentPlayer;
import bb.roborally.data.messages.gameplay.PlayCard;
import bb.roborally.data.util.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import roborally.src.main.java.bb.roborally.data.messages.lobby_messages.*;
import roborally.src.main.java.bb.roborally.data.messages.lobby_messages.SelectMap;

import java.io.IOException;
import java.lang.Error;


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

    @Test
    public void testSendChatSerialization() throws IOException{
        SendChat sendChat = new SendChat();
        String json = sendChat.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SEND_CHAT, envelopeParsed.getMessageType());
        SendChat sendChatParsed = (SendChat) envelopeParsed.getMessageBody();
        assertEquals(sendChat.getMessage(), sendChatParsed.getMessage());
        assertEquals(sendChat.getTo(),sendChatParsed.getTo());
        assertEquals(sendChat.getFrom(),sendChatParsed.getFrom());
        assertEquals(sendChat.isPrivate(),sendChatParsed.isPrivate());
    }

    @Test
    public void testReceivedChatSerialization() throws IOException{
        ReceivedChat receivedChat = new ReceivedChat();
        String json = receivedChat.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.RECEIVED_CHAT, envelopeParsed.getMessageType());
        ReceivedChat receivedChatParsed = (ReceivedChat) envelopeParsed.getMessageBody();
        assertEquals(receivedChat.getMessage(), receivedChatParsed.getMessage());
        assertEquals(receivedChat.getTo(),receivedChatParsed.getTo());
        assertEquals(receivedChat.getFrom(),receivedChatParsed.getFrom());
        assertEquals(receivedChat.isPrivate(),receivedChatParsed.isPrivate());
    }

    @Test
    public void testErrorSerialization() throws IOException{
        Error error = new Error();
        //String json = error.toJson;
        //Envelope envelopeParsed = Envelope.fromJson(json);
        //assertSame(Envelope.MessageType.ERROR, envelopeParsed.getMessageType());
        //Error errorParsed = (Error) envelopeParsed.getMessageBody();
        //assertEquals(error.getError(), errorParsed.getError());
    }

    @Test
    public void testPlayCardSerialization() throws IOException{
        PlayCard playCard = new PlayCard();
        String json = playCard.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAY_CARD, envelopeParsed.getMessageType());
        PlayCard playCardParsed = (PlayCard) envelopeParsed.getMessageBody();
        assertEquals(playCard.getCard(), playCardParsed.getCard());
    }

    @Test
    public void testCardPlayedSerialization() throws IOException{
        CardPlayed cardPlayed = new CardPlayed();
        String json = cardPlayed.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CARD_PLAYED, envelopeParsed.getMessageType());
        CardPlayed cardPlayedParsed = (CardPlayed) envelopeParsed.getMessageBody();
        assertEquals(cardPlayed.getCard(), cardPlayedParsed.getCard());
        assertEquals(cardPlayed.getClientID(), cardPlayedParsed.getClientID());
    }

    @Test
    public void testCurrentPlayerSerialization() throws IOException{
        CurrentPlayer currentPlayer = new CurrentPlayer();
        String json = currentPlayer.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CURRENT_PLAYER, envelopeParsed.getMessageType());
        CurrentPlayer currentPlayerParsed = (CurrentPlayer) envelopeParsed.getMessageBody();
        assertEquals(currentPlayer.getClientID(), currentPlayerParsed.getClientID());
    }

    @Test
    public void testPlayerValuesSerialization()throws IOException{
        PlayerValues playerValues = new PlayerValues();
        String json = PlayerValues.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_VALUES, envelopeParsed.getMessageType());
        PlayerValues playerValuesParsed = (PlayerValues) envelopeParsed.getMessageBody();
        assertEquals(PlayerValues.getName(), playerValuesParsed.getName());
        assertEquals(PlayerValues.getFigure(), playerValuesParsed.getFigure());
    }
    @Test
    public void testPlayerAddedSerialization()throws IOException{
        PlayerAdded playerAdded = new PlayerAdded();
        String json = PlayerAdded.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_ADDED, envelopeParsed.getMessageType());
        PlayerAdded playerAddedParsed = (PlayerAdded) envelopeParsed.getMessageBody();
        assertEquals(PlayerAdded.getClientID(), playerAddedParsed.getClientID());
        assertEquals(PlayerAdded.getName(), playerAddedParsed.getName());
        assertEquals(PlayerAdded.getFigure(), playerAddedParsed.getFigure());
    }

    @Test
    public void testSetStatusSerialization()throws IOException{
        SetStatus setStatus = new SetStatus();
        String json = SetStatus.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SET_STATUS, envelopeParsed.getMessageType());
        SetStatus setStatusParsed = (SetStatus) envelopeParsed.getMessageBody();
        assertEquals(SetStatus.isReady(), setStatusParsed.isReady());
    }

    @Test
    public void testPlayerStatusSerialization()throws IOException{
        PlayerStatus playerStatus = new PlayerStatus();
        String json = PlayerStatus.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_STATUS, envelopeParsed.getMessageType());
        PlayerStatus playerStatusParsed = (PlayerStatus) envelopeParsed.getMessageBody();
        assertEquals(PlayerStatus.getClientID(), playerStatusParsed.getClientID());
        assertEquals(PlayerStatus.isReady(), playerStatusParsed.isReady());
    }

    @Test
    public void testSelectMapSerialization()throws IOException{
        SelectMap selectMap = new SelectMap();
        String json = SelectMap.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SELECT_MAP, envelopeParsed.getMessageType());
        SelectMap selectMapParsed = (SelectMap) envelopeParsed.getMessageBody();
        assertEquals(PlayerStatus.getAvailableMaps(), selectMapParsed.getAvailableMaps());
    }

    @Test
    public void testMapSelectedSerialization()throws IOException{
        MapSelected mapSelected = new MapSelected();
        String json = MapSelected.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.MAP_SELECTED, envelopeParsed.getMessageType());
        MapSelected mapSelectedParsed = (MapSelected) envelopeParsed.getMessageBody();
        assertEquals(MapSelected.getMap(), mapSelectedParsed.getMap());
    }

}

