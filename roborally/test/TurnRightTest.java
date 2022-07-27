import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.TurnRightHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DizzyHighwayBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnRightTest {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new ServerBoard(DizzyHighwayBuilder.buildDizzyHighway()));
    }

    @Test
    public void testMove1() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setRobotOrientation(Orientation.TOP);
        game.getPlayerQueue().add(user1);

        TurnRightHandler turnRightHandler = new TurnRightHandler(server, game, user1);
        turnRightHandler.handle();
        assertEquals(Orientation.RIGHT,user1.getRobot().getRobotOrientation());
    }
}
