import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.RobotList;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.SpamHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.map.DizzyHighwayBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SpamHandlerTests {
    //private static Server server;
    //private static Game game;

    /*@BeforeAll
    public static void init(){
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
    }*/

    @Test
    public void spamCardAddedTest() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        game.getPlayerQueue().add(user1);
        int numberOfSpams = game.getSpamDeck().getSpamDeck().size();
        SpamHandler spamHandler = new SpamHandler(server, game, user1, 2);
        spamHandler.handle();
        assertEquals(numberOfSpams +1, game.getSpamDeck().getSpamDeck().size());
    }

    @Test
    public void spamCardReplacedTest () throws IOException{
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
        User user1 = new User(0);
        RobotList robotList = new RobotList();
        game.getPlayerQueue().add(user1);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,1));
        Spam test = game.getSpamDeck().drawSpamCard();
        user1.getProgram().add(test, 1);
        SpamHandler spamHandler = new SpamHandler(server, game, user1, 1);
        spamHandler.handle();
        //assertEquals(newCard.getName(),user1.getProgram().getCardInRegister(2).getName());
        assertNotEquals(test, user1.getProgram().getCardInRegister(1));
    }
}
