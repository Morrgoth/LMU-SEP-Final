import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move2Handler;
import bb.roborally.server.game.activation.MovementCheck;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.DizzyHighway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MovementCheckTests {

    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(DizzyHighway.buildDizzyHighway()));
    }

    @Test
    public void testmoveOffBoard() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,0));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        assertTrue(movementCheck.checkIfBlockedAlt(user1.getRobot().getPosition(), user1.getRobot().getRobotOrientation()));
    }

    @Test
    public void testMovementCheck() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5,6));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        assertTrue(movementCheck.checkIfBlockedAlt(user1.getRobot().getPosition(), user1.getRobot().getRobotOrientation()));
    }
}
