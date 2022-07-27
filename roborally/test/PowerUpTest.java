import bb.roborally.map.LostBearingsBuilder;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.PowerUpHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.TwisterBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {

    @Test
    public void testPowerUp() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new TwisterBuilder().build().board()));
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        game.getPlayerQueue().add(user1);
        PowerUpHandler powerUpHandler = new PowerUpHandler(server, game, user1);
        powerUpHandler.handle();
        assertEquals(6, user1.getPlayerInventory().getEnergy());
    }
}
