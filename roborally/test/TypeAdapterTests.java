import bb.roborally.data.messages.*;
import bb.roborally.data.messages.Error;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.Alive;
import bb.roborally.data.messages.connection.HelloClient;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.data.messages.gameplay.CardPlayed;
import bb.roborally.data.messages.gameplay.CurrentPlayer;
import bb.roborally.data.messages.gameplay.PlayCard;
import bb.roborally.data.messages.gameplay.*;
import bb.roborally.data.messages.gameplay.*;
import bb.roborally.data.util.User;
import bb.roborally.game.cards.Card;
import com.sun.javafx.binding.StringFormatter;
import javafx.application.Preloader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.util.HashMap;


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
    public void testActivePhaseSerialization() throws IOException{
        ActivePhase activePhase = new ActivePhase();
        String json = activePhase.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.ACTIVE_PHASE, envelopeParsed.getMessageType());
        ActivePhase activePhaseParsed = (ActivePhase) envelopeParsed.getMessageBody();
        assertEquals(activePhase.getPhase(), activePhaseParsed.getPhase());
    }

    @Test
    public void testNotYourCardsSerialization() throws IOException{
        NotYourCards notYourCards = new NotYourCards();
        String json = notYourCards.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.NOT_YOUR_CARDS, envelopeParsed.getMessageType());
        NotYourCards notYourCardsParsed = (NotYourCards) envelopeParsed.getMessageBody();
        assertEquals(notYourCards.getClientID(), notYourCardsParsed.getClientID());
        assertEquals(notYourCards.getCardsInHand(), notYourCardsParsed.getCardsInHand());
    }

    @Test
    public void testSetStartingPointSerialization() throws IOException{
        Welcome welcome = new Welcome();
        String json = welcome.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.WELCOME, envelopeParsed.getMessageType());
        Welcome welcomeParsed = (Welcome) envelopeParsed.getMessageBody();
        assertEquals(welcome.getClientID(), welcomeParsed.getClientID());
    }

    @Test
    public void testShuffleCodingSerialization() throws IOException{
        ShuffleCoding shuffleCoding = new ShuffleCoding();
        String json = shuffleCoding.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SHUFFLE_CODING, envelopeParsed.getMessageType());
        ShuffleCoding shuffleCodingParsed = (ShuffleCoding) envelopeParsed.getMessageBody();
        assertEquals(shuffleCoding.getClientID(), shuffleCodingParsed.getClientID());
    }

    @Test
    public void testStartingPointTakenSerialization() throws IOException{
        StartingPointTaken startingPointTaken = new StartingPointTaken();
        String json = startingPointTaken.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.STARTINGPOINT_TAKEN, envelopeParsed.getMessageType());
        StartingPointTaken startingPointTakenParsed= (StartingPointTaken) envelopeParsed.getMessageBody();
        assertEquals(startingPointTaken.getX(), startingPointTakenParsed.getX());
        assertEquals(startingPointTaken.getY(), startingPointTakenParsed.getY());
        assertEquals(startingPointTaken.getClientID(), startingPointTakenParsed.getClientID());
    }

    @Test
    public void testYourCardsSerialization() throws IOException{
        String[] cardsInHand = {"Move", "Move", "Again"};
        YourCards yourCards = new YourCards(cardsInHand);
        String json = yourCards.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.YOUR_CARDS, envelopeParsed.getMessageType());
        YourCards yourCardsParsed = (YourCards) envelopeParsed.getMessageBody();
        assertArrayEquals(cardsInHand, yourCardsParsed.getCardsInHand());

    }
    @Test
    public void testSelectedCardSerialization() throws IOException {
        SelectedCard selectedCard = new SelectedCard("Move", 2);
        String json = selectedCard.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SELECTED_CARD, envelopeParsed.getMessageType());
        SelectedCard selectedCardParsed = (SelectedCard) envelopeParsed.getMessageBody();
        assertEquals("Move", selectedCardParsed.getCard());
        assertEquals(2, selectedCardParsed.getRegister());
    }

    @Test
    public void testCardSelectedSerialization() throws IOException {
        CardSelected cardSelected = new CardSelected(42, 4, true);
        String json = cardSelected.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CARD_SELECTED, envelopeParsed.getMessageType());
        CardSelected cardSelectedParsed = (CardSelected) envelopeParsed.getMessageBody();
        assertEquals(42, cardSelectedParsed.getClientID());
        assertEquals(4, cardSelectedParsed.getRegister());
        assertTrue(cardSelectedParsed.isFilled());
    }

    @Test
    public void testSelectionFinishedSerialization() throws IOException {
        SelectionFinished selectionFinished = new SelectionFinished(42);
        String json = selectionFinished.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SELECTION_FINISHED, envelopeParsed.getMessageType());
        SelectionFinished selectionFinishedParsed = (SelectionFinished) envelopeParsed.getMessageBody();
        assertEquals(42, selectionFinishedParsed.getClientID());
    }

    @Test
    public void testTimerStartedSerialization() throws IOException {
        TimerStarted timerStarted = new TimerStarted();
        String json = timerStarted.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.TIMER_STARTED, envelopeParsed.getMessageType());
        assertTrue(envelopeParsed.getMessageBody() instanceof TimerStarted);
    }

    @Test
    public void testTimerEndedSerialization() throws IOException {
        int[] array = {1, 2, 3, 4, 5};
        TimerEnded timerEnded = new TimerEnded(array);
        String json = timerEnded.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.TIMER_ENDED, envelopeParsed.getMessageType());
        TimerEnded timerEndedParsed = (TimerEnded) envelopeParsed.getMessageBody();
        assertArrayEquals(array, timerEndedParsed.getClientIDs());
    }

    @Test
    public void testCardsYouGotNowSerialization() throws IOException {
        String[] cards = {"Move", "Move", "Again"};
        CardsYouGotNow cardsYouGotNow = new CardsYouGotNow(cards);
        String json = cardsYouGotNow.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CARDS_YOU_GOT_NOW, envelopeParsed.getMessageType());
        CardsYouGotNow cardsYouGotNowParsed = (CardsYouGotNow) envelopeParsed.getMessageBody();
        assertArrayEquals(cards, cardsYouGotNowParsed.getCards());
    }

    @Test
    public void testCurrentCardsSerialization() throws IOException {
        HashMap<Integer, String> activeCards = new HashMap<>();
        activeCards.put(1, "Move");
        activeCards.put(2, "Spam");
        activeCards.put(3, "Move");
        activeCards.put(4, "Again");
        CurrentCards currentCards = new CurrentCards(activeCards);
        String json = currentCards.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CURRENT_CARDS, envelopeParsed.getMessageType());
        CurrentCards currentCardsParsed = (CurrentCards) envelopeParsed.getMessageBody();
        assertArrayEquals(activeCards.keySet().toArray(new Integer[0]),
                currentCardsParsed.getActiveCards().keySet().toArray(new Integer[0]));
        assertArrayEquals(activeCards.values().toArray(new String[0]),
                currentCardsParsed.getActiveCards().values().toArray(new String[0]));
    }

    @Test
    public void testReplaceCardSerialization() throws IOException {
        ReplaceCard replaceCard = new ReplaceCard(3, "MoveI", 42);
        String json = replaceCard.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.REPLACE_CARD, envelopeParsed.getMessageType());
        ReplaceCard replaceCardParsed = (ReplaceCard) envelopeParsed.getMessageBody();
        assertEquals(3, replaceCardParsed.getRegister());
        assertEquals("MoveI", replaceCardParsed.getNewCard());
        assertEquals(42, replaceCardParsed.getClientID());
    }
    @Test
    public void testSendChatSerialization() throws IOException{
        SendChat sendChat = new SendChat("message", 2,2, true);
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
        ReceivedChat receivedChat = new ReceivedChat("message", 2,2, true);
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
        String json = error.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.ERROR, envelopeParsed.getMessageType());
        Error errorParsed = (Error) envelopeParsed.getMessageBody();
        assertEquals(error.getError(), errorParsed.getError());
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
        CardPlayed cardPlayed = new CardPlayed(2,"message");
        String json = cardPlayed.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CARD_PLAYED, envelopeParsed.getMessageType());
        CardPlayed cardPlayedParsed = (CardPlayed) envelopeParsed.getMessageBody();
        assertEquals(cardPlayed.getClientID(), cardPlayedParsed.getClientID());
        assertEquals(cardPlayed.getCard(), cardPlayedParsed.getCard());
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

}
