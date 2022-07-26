import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.RebootHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.ExtraCrispy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class RebootHandlerTest {

    private static Server server;
    private static Game game;



    @BeforeAll
        public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new ServerBoard(board, ExtraCrispy.buildExtraCrispy()));
    }

    @Test
    public void testRebootPit(){

        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.setStartingPoint(0,3);
        user1.getRobot().setPosition(new Position(6,2));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(1,8);
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);


        RebootHandler rebootHandler = new RebootHandler(server, game, user1);
        rebootHandler.addUser(user1);
        rebootHandler.reboot();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }

    @Test
    public void testrebootFromStartBoard(){
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.setStartingPoint(0,3);
        user1.getRobot().setPosition(new Position(-1,2));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(1,8);
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);


        RebootHandler rebootHandler = new RebootHandler(server, game, user1);
        rebootHandler.addUser(user1);
        rebootHandler.reboot();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }


    @Test
    public void testrebootFromGameBoard(){
        game.setSelectedMap("ExtraCrispy");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.setStartingPoint(0,3);
        user1.getRobot().setPosition(new Position(5,10));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);

        User user2 = new User(1);
        user2.setName("user2");
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user2.setStartingPoint(1,8);
        user2.getRobot().setPosition(new Position(1,1));
        user2.getRobot().setRobotOrientation(Orientation.LEFT);

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);


        RebootHandler rebootHandler = new RebootHandler(server, game, user1);
        rebootHandler.addUser(user1);
        rebootHandler.reboot();

        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

        assertEquals(2, user1.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

        assertEquals(1, user2.getRobot().getPosition().getX());
        assertEquals(1, user2.getRobot().getPosition().getY());

    }


}
