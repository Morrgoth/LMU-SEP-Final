import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.PushPanelActivator;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DeathTrapBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PushPanelTests {
   // private static Server server;
    //private static Game game;

   /* @BeforeAll
    public static void init(){
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
    }*/

    @Test
    public void testPushPanel24With1() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(4,3));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 1);
        pushPanelActivator.activate();
        assertEquals(4, user1.getRobot().getPosition().getX());
        assertEquals(3, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel24With2() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 2));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(2);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 2);
        pushPanelActivator.activate();
        assertEquals(12, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel24With3() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 2));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(3);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 3);
        pushPanelActivator.activate();
        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel24With4() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 2));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(4);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 4);
        pushPanelActivator.activate();
        assertEquals(12, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel24With5() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(2, 2));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(5);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 5);
        pushPanelActivator.activate();
        assertEquals(2, user1.getRobot().getPosition().getX());
        assertEquals(2, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel135With1() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1, 1));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(1);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 1);
        pushPanelActivator.activate();
        assertEquals(12, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel135With2() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1, 1));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(2);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 2);
        pushPanelActivator.activate();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel135With3() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1, 1));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(3);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 3);
        pushPanelActivator.activate();
        assertEquals(12, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel135With4() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1, 1));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(4);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 4);
        pushPanelActivator.activate();
        assertEquals(1, user1.getRobot().getPosition().getX());
        assertEquals(1, user1.getRobot().getPosition().getY());
    }

    @Test
    public void testPushPanel135With5() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new DeathTrapBuilder().build().board()));
        game.setSelectedMap("DeathTrap");

        User user1 = new User(0);
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(1, 1));
        user1.setStartingPoint(new Position(12,3));
        game.getPlayerQueue().add(user1);
        ActivationPhaseHandler.setRegister(5);
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, 5);
        pushPanelActivator.activate();
        assertEquals(12, user1.getRobot().getPosition().getX());
        assertEquals(9, user1.getRobot().getPosition().getY());
    }
}
