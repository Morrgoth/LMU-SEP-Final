import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.PowerUpHandler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.Twister;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(Twister.buildTwister()));
    }

    @Test
    public void testPowerUp() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        game.getPlayerQueue().add(user1);
        PowerUpHandler powerUpHandler = new PowerUpHandler(server, game, user1);
        powerUpHandler.handle();
        assertEquals(6, user1.getRobot().getEnergyCubeAmount());
    }
}
