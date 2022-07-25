import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.activation.TrojanHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.Trojan;
import bb.roborally.map.DizzyHighwayBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TrojanHandlerTests {
    private static Server server;
    private static Game game;
    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
    }

    @Test
    public void spamCardDrawTest() throws IOException {
        User user1 = new User(0);
        game.getPlayerQueue().add(user1);
        int numberOfSpams = game.getSpamDeck().getSpamDeck().size();
        RobotList robotList = new RobotList();
        user1.setRobot(robotList.getRobotByFigureId(2));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        TrojanHandler trojanHandler = new TrojanHandler(server, game, user1, 2);
        trojanHandler.handle();
        assertEquals(numberOfSpams -2, game.getSpamDeck().getSpamDeck().size());
    }

    @Test
    public void trojanCardAddedTest() throws IOException {
        User user1 = new User(0);
        game.getPlayerQueue().add(user1);
        RobotList robotList = new RobotList();
        user1.setRobot(robotList.getRobotByFigureId(2));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        int numberOfTrojan = game.getTrojanDeck().getTrojanDeck().size();
        TrojanHandler trojanHandler = new TrojanHandler(server, game, user1, 2);
        trojanHandler.handle();
        assertEquals(numberOfTrojan +1, game.getTrojanDeck().getTrojanDeck().size());
    }

    @Test
    public void trojanCardReplacedTest () throws IOException{
        User user1 = new User(0);
        RobotList robotList = new RobotList();
        game.getPlayerQueue().add(user1);
        user1.setRobot(robotList.getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1,1));
        Trojan test = game.getTrojanDeck().drawTrojanCard();
        user1.getProgram().add(test, 1);
        User user2 = new User(1);
        user2.setRobot(robotList.getRobotByFigureId(2));
        user2.getRobot().setPosition(new Position(4,2));
        user2.getProgram().add(test,1);
        TrojanHandler trojanHandler = new TrojanHandler(server, game, user1, 1);
        trojanHandler.handle();
        //assertEquals(newCard.getName(),user1.getProgram().getCardInRegister(2).getName());
        assertNotEquals(test, user1.getProgram().getCardInRegister(1));
    }
}
