import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move2Handler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.ExtraCrispy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Veronika Heckel
 */
public class Move2HandlerTest {
   // private static Server server;
    //private static Game game;

   /* @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
    }*/

    @Test
    public void testMove2normalMoveForward() throws IOException {           //Right: erkannt, Bottom: Problem, Left: Problem, Top: Problem
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);

        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,3));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testMove2OffBoard() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");
        User user1 = new User(0);
        User user2 = new User(1);
        user1.setName("user1");
        user2.setName("user2");


        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,0));
        user1.setStartingPoint(new Position(0,6));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,4));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(6, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(4, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());

        //assertThrows(IndexOutOfBoundsException.class, () -> move2Handler.handleAlt());
    }

    @Test
    public void testMove2FallingInPit() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");
        User user1 = new User(0);
        User user2 = new User(1);

        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.setStartingPoint(new Position(0,6));
        user1.getRobot().setPosition(new Position(6,1));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,4));
        user2.getRobot().setPosition(new Position(4, 2));
        user2.getRobot().setRobotOrientation(Orientation.BOTTOM);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(4, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testMove2BlockedByWallOnSameField() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        move2Handler.handleAlt();


        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(4, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testMove2BlockedByWallOnNextField() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        move2Handler.handleAlt();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());
    }

    @Test
    public void moveTwoPushRobot() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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

    @Test
    public void testBlocktByWallBetweenNeighbors() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        user1.getRobot().setPosition(new Position(5,0));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(6,0));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(7,0));
        user3.setStartingPoint(new Position(1,5));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,4));
        user4.getRobot().setPosition(new Position(9,0));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        assertEquals(6, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(7, user2.getRobot().getPosition().getX());
        assertEquals(0, user2.getRobot().getPosition().getY());

        assertEquals(8, user3.getRobot().getPosition().getX());
        assertEquals(0, user3.getRobot().getPosition().getY());

        assertEquals(9, user4.getRobot().getPosition().getX());
        assertEquals(0, user4.getRobot().getPosition().getY());
    }

   @Test
    public void testNullPointerWallTOPPushWallBetweenNeighbors() throws IOException {
       Server server = new Server();
       Game game = server.getGame();
       game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        user1.getRobot().setPosition(new Position(1,5));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(1,4));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.setStartingPoint(new Position(1,4));
        user3.getRobot().setPosition(new Position(1,3));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,8));
        user4.getRobot().setPosition(new Position(1,1));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(4, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(3, user2.getRobot().getPosition().getY());

        assertEquals(1, user3.getRobot().getPosition().getX());
        assertEquals(2, user3.getRobot().getPosition().getY());

        assertEquals(1, user4.getRobot().getPosition().getX());
        assertEquals(1, user4.getRobot().getPosition().getY());

    }

    @Test
    public void testMultiplePlayersFallingInPitMove2() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));

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
        user1.getRobot().setPosition(new Position(2,3));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(3,3));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.setStartingPoint(new Position(0,6));
        user3.getRobot().setPosition(new Position(4,3));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,1));
        user4.getRobot().setPosition(new Position(5,3));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();


        assertEquals(4, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());

        assertEquals(5, user2.getRobot().getPosition().getX());
        assertEquals(3, user2.getRobot().getPosition().getY());

        assertEquals(0, user3.getRobot().getPosition().getX());
        assertEquals(0, user3.getRobot().getPosition().getY());

        assertEquals(2, user3.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user3.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(0, user4.getRobot().getPosition().getX());
        assertEquals(0, user4.getRobot().getPosition().getY());

        assertEquals(2, user4.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user4.getProgrammingDeck().getDiscardPile().get(0).getName());
    }

    @Test
    public void testMultiplePlayersFallingOffBoardMove2() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        user1.getRobot().setPosition(new Position(2,3));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(2,2));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.setStartingPoint(new Position(0,3));
        user3.getRobot().setPosition(new Position(2,1));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(0,6));
        user4.getRobot().setPosition(new Position(2,0));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move2Handler move2Handler = new Move2Handler(server, game, user1);
        move2Handler.handleAlt();

        assertEquals(2 ,user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(0, user2.getRobot().getPosition().getY());

        assertEquals(0, user3.getRobot().getPosition().getX());
        assertEquals(3, user3.getRobot().getPosition().getY());

        assertEquals(2, user3.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user3.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(0, user4.getRobot().getPosition().getX());
        assertEquals(6, user4.getRobot().getPosition().getY());

        assertEquals(2, user4.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user4.getProgrammingDeck().getDiscardPile().get(0).getName());
    }
}
