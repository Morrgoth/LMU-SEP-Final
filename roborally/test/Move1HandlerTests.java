import bb.roborally.map.ExtraCrispyBuilder;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move1Handler;
import bb.roborally.server.game.board.ServerBoard;
//import bb.roborally.server.game.map.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Veronika Heckel
 */

public class  Move1HandlerTests {

    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        //game.setBoard(new ServerBoard(board, ExtraCrispy.buildExtraCrispy()));
    }

    @Test
    public void testMove1normalMoveForward() throws IOException {
        User user1 = new User(0);
        User user2 = new User(1);
        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 3));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(2, 6));
        user2.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(6, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testMove1FallingInPit() throws IOException {

        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(6,1));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setStartingPoint(0,3);
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testMove1OffBoard() throws IOException {

        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,0));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setStartingPoint(0,3);
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testMove1BlockedByWallOnSameField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,2));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,3));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(3, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testMove1BlockedByWallOnNextField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,1));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,3));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);


        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(3, user2.getRobot().getPosition().getY());
    }

    @Test
    public void moveOnePushRobot() throws IOException{
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,0));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(0,1));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(0,2));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(0,3));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());

        assertEquals(0, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());

        assertEquals(0,user3.getRobot().getPosition().getX());
        assertEquals(3, user3.getRobot().getPosition().getY());

        assertEquals(0,user4.getRobot().getPosition().getX());
        assertEquals(4, user4.getRobot().getPosition().getY());
    }

    @Test
    public void moveOnePushRobotWithWall() throws IOException{
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,5));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,4));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(1,3));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(1,2));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(1,user4.getRobot().getPosition().getX());
        assertEquals(2, user4.getRobot().getPosition().getY());

        assertEquals(1,user3.getRobot().getPosition().getX());
        assertEquals(3, user3.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(4, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testBlocktByWallBetweenNeighbors() throws IOException {

        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.setStartingPoint(new Position(1,4));
        user1.getRobot().setPosition(new Position(1,3));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(1,2));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(1,1));
        user3.setStartingPoint(new Position(0,6));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,5));
        user4.getRobot().setPosition(new Position(1,0));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());

        assertEquals(1, user3.getRobot().getPosition().getX());
        assertEquals(1, user3.getRobot().getPosition().getY());

        assertEquals(1, user4.getRobot().getPosition().getX());
        assertEquals(0, user4.getRobot().getPosition().getY());
    }
}
