import bb.roborally.map.ExtraCrispyBuilder;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.BackUpHandler;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.server.game.cards.Move1;
import bb.roborally.server.game.cards.PlayingCard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivationHandlerTest {

    @Test
    public void testPlayingMove1InRegister1() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new ExtraCrispyBuilder().build().board()));

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

        game.getPlayerQueue().getUsers().get(0).getProgram().add(PlayingCard.fromString("MoveI"),1);
        game.getPlayerQueue().getUsers().get(1).getProgram().add(PlayingCard.fromString("TurnLeft"), 1);

        ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(server, game);
        activationPhaseHandler.start();

    }
}
