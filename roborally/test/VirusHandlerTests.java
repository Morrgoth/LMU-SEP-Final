import bb.roborally.map.LostBearingsBuilder;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.RobotList;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.VirusHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.Virus;
import bb.roborally.map.TwisterBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class VirusHandlerTests {

    @Test
    public void testVirus() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new TwisterBuilder().build().board()));


        User user1 = new User(0);
        user1.setName("user1");
        User user2 = new User(1);
        user2.setName("user2");
        User user3 = new User(2);
        user3.setName("user3");
        User user4 = new User(3);
        user3.setName("user4");
        User user5 = new User(4);
        user3.setName("user5");
        User user6 = new User(5);
        user3.setName("user6");

        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user2.setRobot(game.getRobotList().getRobotByFigureId(2));
        user3.setRobot(game.getRobotList().getRobotByFigureId(3));
        user4.setRobot(game.getRobotList().getRobotByFigureId(4));
        user5.setRobot(game.getRobotList().getRobotByFigureId(5));
        user6.setRobot(game.getRobotList().getRobotByFigureId(6));

        user1.getRobot().setPosition(new Position(7, 5));
        user2.getRobot().setPosition(new Position(1, 5));
        user3.getRobot().setPosition(new Position(0, 1));
        user4.getRobot().setPosition(new Position(8, 4));
        user5.getRobot().setPosition(new Position(11, 1));
        user6.getRobot().setPosition(new Position(12, 5));

        game.getPlayerQueue().add(user1);
        game.getPlayerQueue().add(user2);
        game.getPlayerQueue().add(user3);
        game.getPlayerQueue().add(user4);
        game.getPlayerQueue().add(user5);
        game.getPlayerQueue().add(user6);

        VirusHandler virusHandler = new VirusHandler(server, game, user1, 3);
        virusHandler.handle();
        assertNull(user1.getProgram().getCardInRegister(3));
        assertEquals(0, user3.getProgrammingDeck().getDiscardPile().size());
        assertEquals("Spam", user4.getProgrammingDeck().getDiscardPile().get(0).getName());
        assertEquals("Spam", user6.getProgrammingDeck().getDiscardPile().get(0).getName());
        assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
        assertEquals("Spam", user5.getProgrammingDeck().getDiscardPile().get(0).getName());
    }

    @Test
    public void virusCardAddedTest() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new TwisterBuilder().build().board()));

        User user1 = new User(0);
        game.getPlayerQueue().add(user1);
        int numberOfVirus = game.getVirusDeck().getVirusDeck().size();
        VirusHandler virusHandler = new VirusHandler(server, game, user1, 2);
        virusHandler.handle();
        assertEquals(numberOfVirus +1, game.getVirusDeck().getVirusDeck().size());
    }

    @Test
    public void virusCardReplacedTest () throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new TwisterBuilder().build().board()));

        User user1 = new User(0);
        RobotList robotList = new RobotList();
        game.getPlayerQueue().add(user1);
        user1.setRobot(robotList.getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,1));
        Virus test = game.getVirusDeck().drawVirusCard();
        user1.getProgram().add(test, 1);
        VirusHandler virusHandler = new VirusHandler(server, game, user1, 1);
        virusHandler.handle();
        //assertEquals(newCard.getName(),user1.getProgram().getCardInRegister(2).getName());
        assertNotEquals(test, user1.getProgram().getCardInRegister(1));
    }
}
