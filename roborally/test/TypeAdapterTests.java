import bb.roborally.data.messages.*;
import bb.roborally.data.messages.Error;
import bb.roborally.data.messages.chat.ReceivedChat;
import bb.roborally.data.messages.chat.SendChat;
import bb.roborally.data.messages.connection.*;
import bb.roborally.data.messages.gameplay.CardPlayed;
import bb.roborally.data.messages.gameplay.CurrentPlayer;
import bb.roborally.data.messages.gameplay.PlayCard;
import bb.roborally.data.messages.gameplay.*;
import bb.roborally.data.messages.game_events.*;
import bb.roborally.data.messages.lobby.PlayerAdded;
import bb.roborally.data.messages.lobby.PlayerStatus;
import bb.roborally.data.messages.lobby.PlayerValues;
import bb.roborally.data.messages.lobby.SetStatus;
import bb.roborally.data.messages.map.MapSelected;
import bb.roborally.data.messages.map.SelectMap;
import bb.roborally.game.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

public class TypeAdapterTests {

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
        assertEquals(helloServer.getClientID(),helloServerParsed.getClientID());
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
        assertSame(Envelope.MessageType.STARTING_POINT_TAKEN, envelopeParsed.getMessageType());
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
        SendChat sendChat = new SendChat("message", 2);
        String json = sendChat.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SEND_CHAT, envelopeParsed.getMessageType());
        SendChat sendChatParsed = (SendChat) envelopeParsed.getMessageBody();
        assertEquals(sendChat.getMessage(), sendChatParsed.getMessage());
        assertEquals(sendChat.getTo(),sendChatParsed.getTo());
    }

    @Test
    public void testReceivedChatSerialization() throws IOException{
        ReceivedChat receivedChat = new ReceivedChat("message", 2, true);
        String json = receivedChat.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.RECEIVED_CHAT, envelopeParsed.getMessageType());
        ReceivedChat receivedChatParsed = (ReceivedChat) envelopeParsed.getMessageBody();
        assertEquals(receivedChat.getMessage(), receivedChatParsed.getMessage());
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
    public void testMovementSerialization() throws IOException{
        Movement movement = new Movement(42, 4, 2);
        String json = movement.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.MOVEMENT, envelopeParsed.getMessageType());
        Movement movementParsed = (Movement) envelopeParsed.getMessageBody();
        assertEquals(movement.getClientID(), movementParsed.getClientID());
        assertEquals(movement.getX(), movementParsed.getX());
        assertEquals(movement.getY(), movementParsed.getY());
    }

    @Test
    public void testPlayerTurningSerialization() throws IOException{
        PlayerTurning playerTurning = new PlayerTurning(42, "counterclockwise");
        String json = playerTurning.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_TURNING, envelopeParsed.getMessageType());
        PlayerTurning playerTurningParsed = (PlayerTurning) envelopeParsed.getMessageBody();
        assertEquals(playerTurning.getClientID(), playerTurningParsed.getClientID());
        assertEquals(playerTurning.getRotation(), playerTurningParsed.getRotation());
    }

    @Test
    public void testPlayerValuesSerialization()throws IOException{
        PlayerValues playerValues = new PlayerValues("alice", 3);
        String json = playerValues.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_VALUES, envelopeParsed.getMessageType());
        PlayerValues playerValuesParsed = (PlayerValues) envelopeParsed.getMessageBody();
        assertEquals(playerValues.getName(), playerValuesParsed.getName());
        assertEquals(playerValues.getFigure(), playerValuesParsed.getFigure());
    }
    @Test
    public void testPlayerAddedSerialization()throws IOException{
        PlayerAdded playerAdded = new PlayerAdded(42, "alice", 3);
        String json = playerAdded.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_ADDED, envelopeParsed.getMessageType());
        PlayerAdded playerAddedParsed = (PlayerAdded) envelopeParsed.getMessageBody();
        assertEquals(playerAdded.getClientID(), playerAddedParsed.getClientID());
        assertEquals(playerAdded.getName(), playerAddedParsed.getName());
        assertEquals(playerAdded.getFigure(), playerAddedParsed.getFigure());
    }

    @Test
    public void testSetStatusSerialization()throws IOException{
        SetStatus setStatus = new SetStatus();
        String json = setStatus.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SET_STATUS, envelopeParsed.getMessageType());
        SetStatus setStatusParsed = (SetStatus) envelopeParsed.getMessageBody();
        assertEquals(setStatus.isReady(), setStatusParsed.isReady());
    }

    @Test
    public void testAnimationSerialization() throws IOException{
        Animation animation = new Animation("PlayerShooting");
        String json = animation.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.ANIMATION, envelopeParsed.getMessageType());
        Animation animationParsed = (Animation) envelopeParsed.getMessageBody();
        assertEquals(animation.getType(), animationParsed.getType());
    }

    @Test
    public void testRebootSerialization() throws IOException{
        Reboot reboot = new Reboot(42);
        String json = reboot.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.REBOOT, envelopeParsed.getMessageType());
        Reboot rebootParsed = (Reboot) envelopeParsed.getMessageBody();
        assertEquals(reboot.getClientID(), rebootParsed.getClientID());
    }

    @Test
    public void testRebootDirection() throws IOException{
        RebootDirection rebootDirection = new RebootDirection("right");
        String json = rebootDirection.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.REBOOT_DIRECTION, envelopeParsed.getMessageType());
        RebootDirection rebootDirectionParsed = (RebootDirection) envelopeParsed.getMessageBody();
        assertEquals(rebootDirection.getDirection(), rebootDirectionParsed.getDirection());
    }

    @Test
    public void testEnergySerialization() throws IOException{
        Energy energy = new Energy(42, 1, "EnergySpace");
        String json = energy.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.ENERGY, envelopeParsed.getMessageType());
        Energy energyParsed = (Energy) envelopeParsed.getMessageBody();
        assertEquals(42, energyParsed.getClientID());
        assertEquals(1, energyParsed.getCount());
        assertEquals("EnergySpace", energyParsed.getSource());
    }

    @Test
    public void testCheckPointReachedSerialization() throws IOException{
        CheckPointReached checkPointReached = new CheckPointReached(42, 3);
        String json = checkPointReached.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CHECK_POINT_REACHED, envelopeParsed.getMessageType());
        CheckPointReached checkPointReachedParsed = (CheckPointReached) envelopeParsed.getMessageBody();
        assertEquals(42, checkPointReachedParsed.getClientID());
        assertEquals(3, checkPointReachedParsed.getNumber());
    }

    @Test
    public void testGameFinishedSerialization() throws IOException{
        GameFinished gameFinished = new GameFinished(42);
        String json = gameFinished.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.GAME_FINISHED, envelopeParsed.getMessageType());
        GameFinished gameFinishedParsed = (GameFinished) envelopeParsed.getMessageBody();
        assertEquals(42, gameFinishedParsed.getClientID());
    }
    @Test
    public void testPlayerStatusSerialization()throws IOException{
        PlayerStatus playerStatus = new PlayerStatus();
        String json = playerStatus.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PLAYER_STATUS, envelopeParsed.getMessageType());
        PlayerStatus playerStatusParsed = (PlayerStatus) envelopeParsed.getMessageBody();
        assertEquals(playerStatus.getClientID(), playerStatusParsed.getClientID());
        assertEquals(playerStatus.isReady(), playerStatusParsed.isReady());
    }

    @Test
    public void testSelectMapSerialization()throws IOException{
        SelectMap selectMap = new SelectMap("DizzyHighway");
        String json = selectMap.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SELECT_MAP, envelopeParsed.getMessageType());
        SelectMap selectMapParsed = (SelectMap) envelopeParsed.getMessageBody();
        assertEquals(selectMap.getAvailableMaps(), selectMapParsed.getAvailableMaps());
    }

    @Test
    public void testMapSelectedSerialization()throws IOException{
        MapSelected mapSelected = new MapSelected("DizzyRally");
        String json = mapSelected.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.MAP_SELECTED, envelopeParsed.getMessageType());
        MapSelected mapSelectedParsed = (MapSelected) envelopeParsed.getMessageBody();
        assertEquals(mapSelected.getMap(), mapSelectedParsed.getMap());
    }

    @Test
    public void testConnectionUpdateSerialization()throws IOException{
        ConnectionUpdate connectionUpdate = new ConnectionUpdate(2,false, "test");
        String json = connectionUpdate.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.CONNECTION_UPDATE, envelopeParsed.getMessageType());
        ConnectionUpdate connectionUpdateParsed = (ConnectionUpdate) envelopeParsed.getMessageBody();
        assertEquals(connectionUpdate.getClientID(), connectionUpdateParsed.getClientID());
        assertEquals(connectionUpdate.isConnected(),connectionUpdateParsed.isConnected());
        assertEquals(connectionUpdate.getAction(), connectionUpdateParsed.getAction());
    }

    @Test
    public void testDrawDamageSerialization()throws IOException{
        DrawDamage drawDamage = new DrawDamage(2, "test");
        String json = drawDamage.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.DRAW_DAMAGE, envelopeParsed.getMessageType());
        DrawDamage drawDamageParsed = (DrawDamage) envelopeParsed.getMessageBody();
        assertEquals(drawDamage.getClientID(), drawDamageParsed.getClientID());
        assertEquals(drawDamage.getCards(),drawDamageParsed.getCards());
    }

    @Test
    public void testPickDamageSerialization()throws IOException{
        PickDamage pickDamage = new PickDamage(2, "test");
        String json = pickDamage.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.PICK_DAMAGE, envelopeParsed.getMessageType());
        PickDamage pickDamageParsed = (PickDamage) envelopeParsed.getMessageBody();
        assertEquals(pickDamage.getCount(), pickDamageParsed.getCount());
        assertEquals(pickDamage.getAvailablePiles(),pickDamageParsed.getAvailablePiles());
    }

    @Test
    public void testSelectedDamageSerialization()throws IOException{
        SelectedDamage selectedDamage = new SelectedDamage("test");
        String json = selectedDamage.toJson();
        Envelope envelopeParsed = Envelope.fromJson(json);
        assertSame(Envelope.MessageType.SELECTED_DAMAGE, envelopeParsed.getMessageType());
        SelectedDamage selectedDamageParsed = (SelectedDamage) envelopeParsed.getMessageBody();
        assertEquals(selectedDamage.getCards(),selectedDamageParsed.getCards());
    }







}
