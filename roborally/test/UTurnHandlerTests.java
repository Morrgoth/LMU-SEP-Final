import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.UTurnHandler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.DizzyHighway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class UTurnHandlerTests {
    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(DizzyHighway.buildDizzyHighway()));
    }

    @Test
    public void testUTurnHandler() throws IOException{
        User user1 = new User(0);
        User user2 = new User(1);
        User user3 = new User(2);
        User user4 = new User(3);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        for(User user: users){
            for(int i = 0; i<6; i++){
                user.setRobot(game.getRobotList().getRobotByFigureId(i));
            }
        }
        user1.getRobot().setRobotOrientation(Orientation.TOP);
        user2.getRobot().setRobotOrientation(Orientation.LEFT);
        user3.getRobot().setRobotOrientation(Orientation.BOTTOM);
        user4.getRobot().setRobotOrientation(Orientation.RIGHT);
        UTurnHandler uTurnHandler1 = new UTurnHandler(server, game, user1);
        UTurnHandler uTurnHandler2 = new UTurnHandler(server, game, user2);
    }
}
