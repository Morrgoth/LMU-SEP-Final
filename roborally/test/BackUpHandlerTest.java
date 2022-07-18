import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.BackUpHandler;
import bb.roborally.server.game.activation.Move1Handler;
import bb.roborally.server.game.activation.MovementCheck;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.cards.BackUp;
import bb.roborally.server.game.map.DizzyHighway;
import bb.roborally.server.game.map.ExtraCrispy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Veronika Heckel
 */
public class BackUpHandlerTest {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init() {
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
    }

    @Test
    public void testNormalBackUp() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 3));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(3, 6));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        BackUpHandler backUpHandler = new BackUpHandler(server, game, user1);
        backUpHandler.handle();

        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

        assertEquals(3, user2.getRobot().getPosition().getX());
        assertEquals(6, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testBackUpFallingInPit() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0, 2));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

        assertEquals(4, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testBackUpHandlerOffBoard() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0, 0));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);
        BackUpHandler backUpHandler = new BackUpHandler(server, game, user1);
        assertThrows(IndexOutOfBoundsException.class, () -> backUpHandler.handle());
    }


    @Test
    public void testBackUpBlockedByWallOnSameField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,2));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);
        game.getPlayerQueue().add(user1);
        BackUpHandler backUpHandler = new BackUpHandler(server,game,user1);
        backUpHandler.handle();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testBackUpBlockedByWallOnNextField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,1));
        user1.getRobot().setRobotOrientation(Orientation.TOP);
        game.getPlayerQueue().add(user1);
        BackUpHandler backUpHandler = new BackUpHandler(server,game,user1);
        backUpHandler.handle();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());
    }

        @Test
        public void moveOnePushRobot () throws IOException {
            User user1 = new User(0);
            User user2 = new User(1);
            User user3 = new User(2);

            user1.setName("user1");
            user2.setName("user2");
            user3.setName("user3");

            user1.setRobot(game.getRobotList().getRobotByFigureId(1));
            user1.getRobot().setPosition(new Position(0, 0));
            user1.getRobot().setRobotOrientation(Orientation.TOP);

            user2.setRobot(game.getRobotList().getRobotByFigureId(2));
            user2.getRobot().setPosition(new Position(0, 1));
            user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

            user3.setRobot(game.getRobotList().getRobotByFigureId(3));
            user3.getRobot().setPosition(new Position(0, 2));
            user3.getRobot().setRobotOrientation(Orientation.RIGHT);

            game.getPlayerQueue().add(user1);
            game.getPlayerQueue().add(user2);
            game.getPlayerQueue().add(user3);

            BackUpHandler backUpHandler = new BackUpHandler(server,game,user1);
            backUpHandler.handle();

            assertEquals(0, user1.getRobot().getPosition().getX());
            assertEquals(1, user1.getRobot().getPosition().getY());

            assertEquals(0, user2.getRobot().getPosition().getX());
            assertEquals(2, user2.getRobot().getPosition().getY());

            assertEquals(0, user3.getRobot().getPosition().getX());
            assertEquals(3, user3.getRobot().getPosition().getY());
        }

        @Test
        public void moveOnePushBackRobotwithWall () throws IOException {
            User user1 = new User(0);
            User user2 = new User(1);
            User user3 = new User(2);

            user1.setName("user1");
            user2.setName("user2");
            user3.setName("user3");

            user1.setRobot(game.getRobotList().getRobotByFigureId(1));
            user1.getRobot().setPosition(new Position(1, 4));
            user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

            user2.setRobot(game.getRobotList().getRobotByFigureId(2));
            user2.getRobot().setPosition(new Position(1, 3));
            user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

            user3.setRobot(game.getRobotList().getRobotByFigureId(3));
            user3.getRobot().setPosition(new Position(1, 2));
            user3.getRobot().setRobotOrientation(Orientation.RIGHT);

            game.getPlayerQueue().add(user1);
            game.getPlayerQueue().add(user2);
            game.getPlayerQueue().add(user3);

            BackUpHandler backUpHandler = new BackUpHandler(server,game,user1);
            backUpHandler.handle();

            assertEquals(1, user1.getRobot().getPosition().getX());
            assertEquals(4, user1.getRobot().getPosition().getY());

            assertEquals(1, user2.getRobot().getPosition().getX());
            assertEquals(3, user2.getRobot().getPosition().getY());

            assertEquals(1, user3.getRobot().getPosition().getX());
            assertEquals(2, user3.getRobot().getPosition().getY());
    }

    }


