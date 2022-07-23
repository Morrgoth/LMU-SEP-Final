import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move1Handler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Veronika Heckel
 */

public class  Move1HandlerTests {

    //private static Server server;
    //private static Game game;

   /* @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
    }*/

    @Test
    public void testMove1normalMoveForward_LEFT() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
    public void testMove1normalMoveForward_RIGHT() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 3));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(2, 6));
        user2.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(3, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(6, user2.getRobot().getPosition().getY());
    }

    @Test
    public void testMove1normalMoveForward_TOP() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 3));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(2, 6));
        user2.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(6, user2.getRobot().getPosition().getY());
    }


    @Test
    public void testMove1normalMoveForward_BOTTOM() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        user1.setName("user1");
        user2.setName("user2");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 3));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(2, 6));
        user2.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(4, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(6, user2.getRobot().getPosition().getY());
    }




    @Test
    public void testMove1FallingInPit_BOTTOM() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
    public void testMove1FallingInPit_TOP() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(6,4));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

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
    public void testMove1FallingInPit_LEFT() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(7,2));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

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
    public void testMove1FallingInPit_RIGHT() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(8,2));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

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
    public void testMove1OffBoard_LEFT() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
    public void testMove1OffBoard_RIGHT() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(12,8));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

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


        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testMove1OffBoard_TOP() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(12,0));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

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


        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testMove1OffBoard_BOTTOM() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setStartingPoint(1,1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(12,9));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

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


        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testMove1BlockedByWallOnSameField() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
    public void moveOnePushRobot_BOTTOM() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
    public void moveOnePushRobot_TOP() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2,4));
        user1.getRobot().setRobotOrientation(Orientation.TOP);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(2,3));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(2,2));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(2,1));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(2, user2.getRobot().getPosition().getY());

        assertEquals(2,user3.getRobot().getPosition().getX());
        assertEquals(1, user3.getRobot().getPosition().getY());

        assertEquals(2,user4.getRobot().getPosition().getX());
        assertEquals(0, user4.getRobot().getPosition().getY());
    }


    @Test
    public void moveOnePushRobot_LEFT() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5,0));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4,0));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(3,0));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(2,0));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(4, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(3, user2.getRobot().getPosition().getX());
        assertEquals(0, user2.getRobot().getPosition().getY());

        assertEquals(2,user3.getRobot().getPosition().getX());
        assertEquals(0, user3.getRobot().getPosition().getY());

        assertEquals(1,user4.getRobot().getPosition().getX());
        assertEquals(0, user4.getRobot().getPosition().getY());
    }


    @Test
    public void moveOnePushRobot_RIGHT() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,0));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(2,0));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(3,0));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();


        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user2.getRobot().getPosition().getX());
        assertEquals(0, user2.getRobot().getPosition().getY());

        assertEquals(3,user3.getRobot().getPosition().getX());
        assertEquals(0, user3.getRobot().getPosition().getY());

        assertEquals(4,user4.getRobot().getPosition().getX());
        assertEquals(0, user4.getRobot().getPosition().getY());
    }


    @Test
    public void moveOnePushRobotWithWall_TOP() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
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
    public void moveOnePushRobotWithWall_BOTTOM() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(3,0));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(3,1));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(3,2));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(3,3));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(3, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(3, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

        assertEquals(3,user3.getRobot().getPosition().getX());
        assertEquals(2, user3.getRobot().getPosition().getY());

        assertEquals(3,user4.getRobot().getPosition().getX());
        assertEquals(3, user4.getRobot().getPosition().getY());

    }

    @Test
    public void moveOnePushRobotWithWall_LEFT() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(7,9));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(8,9));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(9,9));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(10,9));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(7, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());

        assertEquals(8, user2.getRobot().getPosition().getX());
        assertEquals(9, user2.getRobot().getPosition().getY());

        assertEquals(9,user3.getRobot().getPosition().getX());
        assertEquals(9, user3.getRobot().getPosition().getY());

        assertEquals(10,user4.getRobot().getPosition().getX());
        assertEquals(9, user4.getRobot().getPosition().getY());
    }

    @Test
    public void moveOnePushRobotWithWall_RIGHT() throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);

        user1.setName("user1");
        user2.setName("user2");
        user3.setName("user3");
        user4.setName("user4");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(0,9));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(1,9));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(2,9));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.getRobot().setPosition(new Position(3,9));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(9, user2.getRobot().getPosition().getY());

        assertEquals(2,user3.getRobot().getPosition().getX());
        assertEquals(9, user3.getRobot().getPosition().getY());

        assertEquals(3,user4.getRobot().getPosition().getX());
        assertEquals(9, user4.getRobot().getPosition().getY());
    }


    @Test
    public void testBlocktByWallBetweenNeighbors_TOP() throws IOException {
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

    @Test
    public void testBlocktByWallBetweenNeighbors_BOTTOM() throws IOException {
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
        user1.getRobot().setPosition(new Position(1,0));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.setStartingPoint(new Position(0,6));
        user3.getRobot().setPosition(new Position(1,2));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,5));
        user4.getRobot().setPosition(new Position(1,3));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

        assertEquals(1, user3.getRobot().getPosition().getX());
        assertEquals(2, user3.getRobot().getPosition().getY());

        assertEquals(1, user4.getRobot().getPosition().getX());
        assertEquals(3, user4.getRobot().getPosition().getY());
    }

    @Test
    public void testBlocktByWallBetweenNeighbors_LEFT() throws IOException {
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
        user1.getRobot().setPosition(new Position(5,5));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(4,5));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(3,5));
        user3.setStartingPoint(new Position(0,6));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,5));
        user4.getRobot().setPosition(new Position(2,5));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(5, user1.getRobot().getPosition().getX());
        assertEquals(5, user1.getRobot().getPosition().getY());

        assertEquals(4, user2.getRobot().getPosition().getX());
        assertEquals(5, user2.getRobot().getPosition().getY());

        assertEquals(3, user3.getRobot().getPosition().getX());
        assertEquals(5, user3.getRobot().getPosition().getY());

        assertEquals(2, user4.getRobot().getPosition().getX());
        assertEquals(5, user4.getRobot().getPosition().getY());
    }

    @Test
    public void testBlocktByWallBetweenNeighbors_RIGHT() throws IOException {
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
        user1.getRobot().setPosition(new Position(4,5));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);

        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(new Position(1,8));
        user2.getRobot().setPosition(new Position(5,5));
        user2.getRobot().setRobotOrientation(Orientation.RIGHT);

        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user3.getRobot().setPosition(new Position(6,5));
        user3.setStartingPoint(new Position(0,6));
        user3.getRobot().setRobotOrientation(Orientation.LEFT);

        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user4.setStartingPoint(new Position(1,5));
        user4.getRobot().setPosition(new Position(7,5));
        user4.getRobot().setRobotOrientation(Orientation.TOP);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);

        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();

        assertEquals(4, user1.getRobot().getPosition().getX());
        assertEquals(5, user1.getRobot().getPosition().getY());

        assertEquals(5, user2.getRobot().getPosition().getX());
        assertEquals(5, user2.getRobot().getPosition().getY());

        assertEquals(6, user3.getRobot().getPosition().getX());
        assertEquals(5, user3.getRobot().getPosition().getY());

        assertEquals(7, user4.getRobot().getPosition().getX());
        assertEquals(5, user4.getRobot().getPosition().getY());
    }


}
