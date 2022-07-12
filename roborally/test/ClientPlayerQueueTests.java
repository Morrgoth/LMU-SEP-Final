import bb.roborally.client.player_list.Player;
import bb.roborally.client.player_list.PlayerQueue;
import bb.roborally.client.robot_selector.Robot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientPlayerQueueTests {

    @Test
    public void testPlayerQueueInitialization() {
        PlayerQueue playerQueue = new PlayerQueue();
        assertEquals(1, playerQueue.size());
        assertNotNull(playerQueue.getLocalPlayer());
        assertEquals(Player.NO_ID, playerQueue.getLocalPlayer().getId());
        assertFalse(playerQueue.getLocalPlayer().isIdSet());
        assertFalse(playerQueue.getLocalPlayer().isAdded());
        assertFalse(playerQueue.getLocalPlayer().isReady());
        assertFalse(playerQueue.getLocalPlayer().isMapSelector());
    }

    @Test
    public void testSettingLocalPlayerId() {
        PlayerQueue playerQueue = new PlayerQueue();
        playerQueue.setLocalPlayerId(0);
        assertEquals(1, playerQueue.size());
        assertNotNull(playerQueue.getLocalPlayer());
        assertEquals(0, playerQueue.getLocalPlayer().getId());
        assertTrue(playerQueue.getLocalPlayer().isIdSet());
        assertFalse(playerQueue.getLocalPlayer().isAdded());
        assertFalse(playerQueue.getLocalPlayer().isReady());
        assertFalse(playerQueue.getLocalPlayer().isMapSelector());
    }

    @Test
    public void testAddingLocalPlayer() {
        PlayerQueue playerQueue = new PlayerQueue();
        playerQueue.setLocalPlayerId(0);
        playerQueue.getLocalPlayer().add("Johnny", new Robot(1, "Twonky"));
        assertEquals(1, playerQueue.size());
        assertNotNull(playerQueue.getLocalPlayer());
        assertEquals(0, playerQueue.getLocalPlayer().getId());
        assertTrue(playerQueue.getLocalPlayer().isIdSet());
        assertTrue(playerQueue.getLocalPlayer().isAdded());
        assertFalse(playerQueue.getLocalPlayer().isReady());
        assertFalse(playerQueue.getLocalPlayer().isMapSelector());
    }

    @Test
    public void testAddingLocalPlayerAfterAddingAnotherPlayer() {
        PlayerQueue playerQueue = new PlayerQueue();
        playerQueue.addPlayer(1, "Toma", new Robot(2, "Hulk"));
        playerQueue.setLocalPlayerId(0);
        playerQueue.getLocalPlayer().add("Johnny", new Robot(1, "Twonky"));
        assertEquals(2, playerQueue.size());
        assertNotNull(playerQueue.getLocalPlayer());
        assertEquals(0, playerQueue.getLocalPlayer().getId());
        assertTrue(playerQueue.getLocalPlayer().isIdSet());
        assertTrue(playerQueue.getLocalPlayer().isAdded());
        assertFalse(playerQueue.getLocalPlayer().isReady());
        assertFalse(playerQueue.getLocalPlayer().isMapSelector());
        assertTrue(playerQueue.getPlayerById(1).isIdSet());
        assertTrue(playerQueue.getPlayerById(1).isAdded());
        assertFalse(playerQueue.getPlayerById(1).isReady());
        assertFalse(playerQueue.getPlayerById(1).isMapSelector());

    }
}
