import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move1Handler;
import bb.roborally.server.game.activation.Move2Handler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.DeathTrap;
import bb.roborally.server.game.map.DizzyHighway;
import bb.roborally.server.game.map.ExtraCrispy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class Move2HandlerTest {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
    }

    @Test
    public void testMove2normalMoveForward() throws IOException {           //Right: erkannt, Bottom: Problem, Left: Problem, Top: Problem
        User user1 = new User(0);
        User user2 = new User(1);

        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 2));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        //move2Handler.handle(0);
        move2Handler.handleAlt();
        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(4, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testMove1OffBoard(){
        User user1 = new User(0);
        User user2 = new User(1);
        user1.setName("user1");
        user2.setName("user2");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));

        user1.getRobot().setPosition(new Position(0,0));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        assertThrows(IndexOutOfBoundsException.class, () -> move2Handler.handleAlt());
    }

    @Test
    public void testMove2FallingInPit() throws IOException {
        User user1 = new User(0);
        User user2 = new User(1);

        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(6,1));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();
        assertEquals(6, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

    }

    @Test
    public void testMove2BlockedByWallOnSameField() throws IOException{
        User user1 = new User(0);
        User user2 = new User(1);

        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,4));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        //move2Handler.handle(2);
        move2Handler.handleAlt();

        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(4, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testMove2BlockedByWallOnNextField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        User user2 = new User(1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,0));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        Move2Handler move2Handler = new Move2Handler(server, game, user1);

        //move2Handler.handle(2);
        move2Handler.handleAlt();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());
    }

    @Test
    public void moveTwoPushRobot() throws IOException{
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,0));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(0,1));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(0,2));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

        assertEquals(0, user2.getRobot().getPosition().getX());
        assertEquals(3, user2.getRobot().getPosition().getY());

        assertEquals(0, user3.getRobot().getPosition().getX());
        assertEquals(4, user3.getRobot().getPosition().getY());
    }

    @Test
    public void moveTwoPushRobotBlockedByWall() throws IOException{
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5,0));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(6,0));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(7,0));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();


        assertEquals(6, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(7, user2.getRobot().getPosition().getX());
        assertEquals(0, user2.getRobot().getPosition().getY());

        assertEquals(8, user3.getRobot().getPosition().getX());
        assertEquals(0, user3.getRobot().getPosition().getY());





    }
}
