import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.SpamHandler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.map.DizzyHighway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpamHandlerTests {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(DizzyHighway.buildDizzyHighway()));
    }

    @Test
    public void spamCardAddedTest() throws IOException {
        User user1 = new User(0);
        game.getPlayerQueue().add(user1);
        int numberOfSpams = game.getSpamDeck().getSpamDeck().size();
        SpamHandler spamHandler = new SpamHandler(server, game, user1, 2);
        spamHandler.handle();
        assertEquals(numberOfSpams +1, game.getSpamDeck().getSpamDeck().size());
    }

    @Test
    public void spamCardReplacedTest () throws IOException{
        User user1 = new User(0);
        game.getPlayerQueue().add(user1);
        PlayingCard newCard = user1.getPlayerInventory().getProgrammingDeck().draw();
        SpamHandler spamHandler = new SpamHandler(server, game, user1, 2);
        spamHandler.handle();
        //assertEquals(newCard.getName(),user1.getProgram().getCardInRegister(2).getName());

        assertEquals(newCard.getName(),user1.getProgrammingDeck().getDiscardPile().get(0).getName());
    }
}
