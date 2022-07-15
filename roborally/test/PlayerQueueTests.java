import bb.roborally.protocol.Message;
import bb.roborally.protocol.lobby.PlayerAdded;
import bb.roborally.protocol.lobby.PlayerStatus;
import bb.roborally.server.game.PlayerQueue;
import bb.roborally.server.game.RobotList;
import bb.roborally.server.game.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerQueueTests {

    @Test
    public void testAdd() {
        PlayerQueue playerQueue = new PlayerQueue(2);
        User user = new User(42);
        User user1 = new User(13);
        playerQueue.add(user);
        assertTrue(playerQueue.contains(user));
        assertFalse(playerQueue.contains(user1));
        playerQueue.add(user1);
        assertTrue(playerQueue.contains(user1));
        assertTrue(playerQueue.contains(13));
        assertFalse(playerQueue.isMapSelectorAvailable());
        assertFalse(playerQueue.isGameReadyToStart());
    }

    @Test
    public void testUpdate() {
        PlayerQueue playerQueue = new PlayerQueue(2);
        User user = new User(42);
        playerQueue.add(user);
        assertFalse(playerQueue.getUserById(42).isReady());
        assertFalse(playerQueue.isMapSelectorAvailable());
        assertFalse(playerQueue.isMapSelectorNotified());
        assertFalse(playerQueue.isGameReadyToStart());
        playerQueue.update(new PlayerStatus(42, true));
        assertTrue(playerQueue.getUserById(42).isReady());
        assertTrue(playerQueue.isMapSelectorAvailable());
        assertEquals(42, playerQueue.getMapSelectorClientId());
        assertFalse(playerQueue.isGameReadyToStart());
        playerQueue.update(new PlayerStatus(42, false));
        assertFalse(playerQueue.getUserById(42).isReady());
        assertFalse(playerQueue.isMapSelectorAvailable());
        assertEquals(-1, playerQueue.getMapSelectorClientId());
        User user1 = new User(13);
        playerQueue.add(user1);
        assertFalse(playerQueue.isGameReadyToStart());
        assertFalse(playerQueue.isMapSelectorAvailable());
        playerQueue.update(new PlayerStatus(13, true));
        assertFalse(playerQueue.isGameReadyToStart());
        assertTrue(playerQueue.isMapSelectorAvailable());
        assertFalse(playerQueue.isMapSelectorNotified());
        assertEquals(13, playerQueue.getMapSelectorClientId());
        playerQueue.update(new PlayerStatus(42, true));
        playerQueue.update(new PlayerStatus(41, true));
        assertTrue(playerQueue.isGameReadyToStart());
        assertEquals(13, playerQueue.getMapSelectorClientId());
    }

    @Test
    public void testRemove() {
        PlayerQueue playerQueue = new PlayerQueue(2);
        User user = new User(42);
        playerQueue.add(user);
        assertTrue(playerQueue.contains(42));
        assertFalse(playerQueue.isMapSelectorAvailable());
        assertFalse(playerQueue.isGameReadyToStart());
        playerQueue.remove(42);
        assertFalse(playerQueue.isMapSelectorAvailable());
        playerQueue.add(user);
        playerQueue.update(new PlayerStatus(42, true));
        assertTrue(playerQueue.isMapSelectorAvailable());
        assertFalse(playerQueue.isGameReadyToStart());
        playerQueue.remove(42);
        assertFalse(playerQueue.isMapSelectorAvailable());
        assertFalse(playerQueue.isGameReadyToStart());
    }

    @Test
    public void testGeneratePlayersUpdate() {
        PlayerQueue playerQueue = new PlayerQueue(2);
        RobotList robotList = new RobotList();
        User user = new User(42);
        User user1 = new User(13);
        user.setRobot(robotList.getRobotByFigureId(1));
        user1.setRobot(robotList.getRobotByFigureId(3));
        playerQueue.add(user);
        playerQueue.add(user1);
        playerQueue.update(new PlayerStatus(42, true));
        ArrayList<Message> messages = playerQueue.generatePlayersUpdate();
        assertEquals(4, messages.size());
        assertTrue(messages.get(0) instanceof PlayerAdded);
        assertTrue(messages.get(1) instanceof PlayerStatus);
        assertTrue(messages.get(2) instanceof PlayerAdded);
        assertTrue(messages.get(3) instanceof PlayerStatus);
    }
}
