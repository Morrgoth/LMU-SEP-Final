import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.UTurnHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DizzyHighwayBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UTurnHandlerTests {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
    }

    @Test
    public void testUTurnHandler() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        User user2 = new User(1);
        user2.setName("user2");
        User user3 = new User(2);
        user3.setName("user3");
        User user4 = new User(3);
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user4.setRobot(game.getRobotList().getRobotByFigureId(4));

        user1.getRobot().setRobotOrientation(Orientation.TOP);
        user2.getRobot().setRobotOrientation(Orientation.LEFT);
        user3.getRobot().setRobotOrientation(Orientation.BOTTOM);
        user4.getRobot().setRobotOrientation(Orientation.RIGHT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        UTurnHandler uTurnHandler1 = new UTurnHandler(server, game, user1);
        uTurnHandler1.handle();
        UTurnHandler uTurnHandler2 = new UTurnHandler(server, game, user2);
        uTurnHandler2.handle();
        UTurnHandler uTurnHandler3 = new UTurnHandler(server, game, user3);
        uTurnHandler3.handle();
        UTurnHandler uTurnHandler4 = new UTurnHandler(server, game, user4);
        uTurnHandler4.handle();

        assertEquals(Orientation.BOTTOM, user1.getRobot().getRobotOrientation());
        assertEquals(Orientation.RIGHT, user2.getRobot().getRobotOrientation());
        assertEquals(Orientation.TOP, user3.getRobot().getRobotOrientation());
        assertEquals(Orientation.LEFT, user4.getRobot().getRobotOrientation());

    }
}
