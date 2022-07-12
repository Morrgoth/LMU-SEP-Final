import bb.roborally.protocol.game_events.Movement;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.Move1Handler;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.DizzyHighway;
import bb.roborally.server.game.map.ExtraCrispy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class  Move1HandlerTests {

    private static Server server;
    private static Game game;

    @BeforeAll
    public static void init(){
        server = new Server();
        game = server.getGame();
        game.setBoard(new Board(ExtraCrispy.buildExtraCrispy()));
    }

    @Test
    public void testMove1normalMoveForward() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 2));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);
        game.getPlayerQueue().add(user1);
        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();
        assertEquals(3, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());


    }

    @Test
    public void testMove1FallingInPit() throws IOException {
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(7,7));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        game.getPlayerQueue().add(user1);
        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();
        assertEquals(0, user1.getRobot().getPosition().getX());
        assertEquals(0, user1.getRobot().getPosition().getY());

    }

    @Test
    public void testMove1BlockedByWallOnSameField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(6,5));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        game.getPlayerQueue().add(user1);
        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();
        assertEquals(6, user1.getRobot().getPosition().getX());
        assertEquals(5, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testMove1BlockedByWallOnNextField() throws IOException{
        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(3,6));
        user1.getRobot().setRobotOrientation(Orientation.TOP);
        game.getPlayerQueue().add(user1);
        Move1Handler move1Handler = new Move1Handler(server, game, user1);
        move1Handler.handle();
        assertEquals(3, user1.getRobot().getPosition().getX());
        assertEquals(6, user1.getRobot().getPosition().getY());
    }

}
