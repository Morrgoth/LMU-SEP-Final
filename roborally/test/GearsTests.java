import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.GearActivator;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.LostBearingsBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GearsTests {
    @Test
    public void testGearsCounterclockwiseLeft() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 4));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.BOTTOM, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsCounterclockwiseRight() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 4));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.TOP, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsCounterclockwiseTop() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 4));
        user1.getRobot().setRobotOrientation(Orientation.TOP);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.LEFT, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsCounterclockwiseBottom() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 4));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.RIGHT, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsClockwiseLeft() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 5));
        user1.getRobot().setRobotOrientation(Orientation.LEFT);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.TOP, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsClockwiseRight() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 5));
        user1.getRobot().setRobotOrientation(Orientation.RIGHT);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.BOTTOM, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsClockwiseTop() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 5));
        user1.getRobot().setRobotOrientation(Orientation.TOP);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.RIGHT, user1.getRobot().getRobotOrientation());
    }

    @Test
    public void testGearsClockwiseBottom() throws IOException {
        Server server = new Server();
        Game game = server.getGame();
        game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));

        User user1 = new User(0);
        user1.setName("user1");
        user1.setRobot(game.getRobotList().getRobotByFigureId(1));
        user1.getRobot().setPosition(new Position(5, 5));
        user1.getRobot().setRobotOrientation(Orientation.BOTTOM);
        game.getPlayerQueue().add(user1);
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        assertEquals(Orientation.LEFT, user1.getRobot().getRobotOrientation());
    }
}
