import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move1Handler;
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
    public void testcheckIfBlockedAlt() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5,6));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);
        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        assertTrue(movementCheck.checkIfBlockedAlt(user1.getRobot().getPosition(), user1.getRobot().getRobotOrientation()));
    }

    @Test
    public void testcheckRobotForward(){
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,0));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,0));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        /*user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(2,0));
        user3.getRobot().setRobotOrientation(Orientation.RIGHT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);

        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        movementCheck.robotForwardCheckForOneStep(user1.getRobot().getPosition(), user1.getRobot().getRobotOrientation());
        assertTrue(true);*/
    }

    @Test
    public void testPushRobotForward() throws IOException {
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,0));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,0));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(2,0));
        user3.getRobot().setRobotOrientation(Orientation.RIGHT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);

        /*Move1Handler move1Handler = new Move1Handler(server, game,user1);
        move1Handler.handle();*/

        MovementCheck movementCheck = new MovementCheck(game.getBoard(), game);
        movementCheck.pushRobot(server, game, user1, user1.getRobot().getRobotOrientation());


        /*assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());*/

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(0, user2.getRobot().getPosition().getY());

        assertEquals(3, user3.getRobot().getPosition().getX());
        assertEquals(0, user3.getRobot().getPosition().getY());


    }
}
