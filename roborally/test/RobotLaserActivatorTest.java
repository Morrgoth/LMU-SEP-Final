import bb.roborally.map.DizzyHighwayBuilder;
import bb.roborally.map.LostBearingsBuilder;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.BoardLaserActivator;
import bb.roborally.server.game.activation.RobotLaserActivator;
import bb.roborally.server.game.board.ServerBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RobotLaserActivatorTest {
	//DizzyHighway
	//1.robot on opposite sides of map OrientationRight - should shoot
	@Test
	public void testONE_DH() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));

		user1.getRobot().setPosition(new Position(3, 0));
		user2.getRobot().setPosition(new Position(9, 0));

		user1.getRobot().setRobotOrientation(Orientation.RIGHT);
		user2.getRobot().setRobotOrientation(Orientation.TOP);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());

	}
	//2. robot on opposite sides of map Orientation wrong- should NOT shoot
	@Test
	public void testTWO_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));

		user1.getRobot().setPosition(new Position(9, 0));
		user2.getRobot().setPosition(new Position(3, 0));

		user1.getRobot().setRobotOrientation(Orientation.BOTTOM);
		user2.getRobot().setRobotOrientation(Orientation.TOP);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertNotEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}

	//2. one robot behind a wall
	@Test
	public void testTHREE_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));

		user1.getRobot().setPosition(new Position(2, 4));
		user2.getRobot().setPosition(new Position(3, 4));

		user1.getRobot().setRobotOrientation(Orientation.RIGHT);
		user2.getRobot().setRobotOrientation(Orientation.TOP);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertNotEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}
	//3. Antenn in between
	@Test
	public void testFOUR_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));

		user1.getRobot().setPosition(new Position(0, 5));
		user2.getRobot().setPosition(new Position(0, 3));

		user1.getRobot().setRobotOrientation(Orientation.TOP);
		user2.getRobot().setRobotOrientation(Orientation.RIGHT);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		//assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}
	//3. robot behind the shooter - not shooting
	@Test
	public void testFIVE_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));

		user1.getRobot().setPosition(new Position(9, 0));
		user2.getRobot().setPosition(new Position(8, 0));

		user1.getRobot().setRobotOrientation(Orientation.RIGHT);
		user2.getRobot().setRobotOrientation(Orientation.TOP);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertNotEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}
	//4.three robots in line
	@Test
	public void testSIX_DH() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		User user3 = new User(2);
		user1.setName("user1");
		user2.setName("user2");
		user3.setName("user3");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user3.setRobot(game.getRobotList().getRobotByFigureId(3));

		user1.getRobot().setPosition(new Position(2, 1));
		user2.getRobot().setPosition(new Position(2, 2));
		user3.getRobot().setPosition(new Position(2, 4));

		user1.getRobot().setRobotOrientation(Orientation.BOTTOM);
		user2.getRobot().setRobotOrientation(Orientation.TOP);
		user3.getRobot().setRobotOrientation(Orientation.TOP);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);
		game.getPlayerQueue().add(user3);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(2, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(1).getName());
		assertEquals(numberOfSpams - 3, game.getSpamDeck().getSpamDeck().size());
	}

	//5.three robots in line 2shooting1 in the middle
	@Test
	public void testSEVEN_DH() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		User user3 = new User(2);
		user1.setName("user1");
		user2.setName("user2");
		user3.setName("user3");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user3.setRobot(game.getRobotList().getRobotByFigureId(3));

		user1.getRobot().setPosition(new Position(3, 2));
		user2.getRobot().setPosition(new Position(4, 2));
		user3.getRobot().setPosition(new Position(6, 2));

		user1.getRobot().setRobotOrientation(Orientation.RIGHT);
		user2.getRobot().setRobotOrientation(Orientation.TOP);
		user3.getRobot().setRobotOrientation(Orientation.LEFT);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);
		game.getPlayerQueue().add(user3);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user3.getProgrammingDeck().getDiscardPile().size());
		assertEquals(2, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(1).getName());
		assertEquals(numberOfSpams - 2, game.getSpamDeck().getSpamDeck().size());
	}
	@Test
	public void testEIGHT_DH() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		User user3 = new User(2);
		user1.setName("user1");
		user2.setName("user2");
		user3.setName("user3");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user3.setRobot(game.getRobotList().getRobotByFigureId(3));

		user1.getRobot().setPosition(new Position(3, 2));
		user2.getRobot().setPosition(new Position(4, 2));
		user3.getRobot().setPosition(new Position(6, 2));

		user1.getRobot().setRobotOrientation(Orientation.RIGHT);
		user2.getRobot().setRobotOrientation(Orientation.RIGHT);
		user3.getRobot().setRobotOrientation(Orientation.LEFT);

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);
		game.getPlayerQueue().add(user3);

		RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server,game,1);
		robotLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user3.getProgrammingDeck().getDiscardPile().size());
		assertEquals(2, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user3.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(1).getName());
		assertEquals(numberOfSpams - 3, game.getSpamDeck().getSpamDeck().size());
	}
	//DeathTrap
	//1.robot behind opposite wall on both sides
	@Test
	public void testONE_LB() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));

		user1.getRobot().setPosition(new Position(10, 3));
		user2.getRobot().setPosition(new Position(5, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertFalse( user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse( user2.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertEquals(game.getSpamDeck().getSpamDeck().size(),game.getSpamDeck().getSpamDeck().size());
	}
	//2. one inside on laser tile; one outside
	@Test
	public void testTWO_LB() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(5, 3));
		game.getPlayerQueue().add(user1);

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 3));
		game.getPlayerQueue().add(user2);


		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}

	//2. one inside farther away; one outside
	@Test
	public void testTHREE_LB() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(5, 3));
		game.getPlayerQueue().add(user1);

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(9, 3));
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams -1 , game.getSpamDeck().getSpamDeck().size());
	}
	//3. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testFOUR_LB() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(9, 3));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertTrue(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams -1 , game.getSpamDeck().getSpamDeck().size());
	}
	//5. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testFIVE_LB() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 3));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(8, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams -1, game.getSpamDeck().getSpamDeck().size());
	}
	//4.both robots outside laser left right
	@Test
	public void testSIX_LB() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(8, 4));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 2));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();


		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertEquals(game.getSpamDeck().getSpamDeck().size(), game.getSpamDeck().getSpamDeck().size());
	}

	//5.behind both robots outside right
	@Test
	public void testSEVEN_LB() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(10, 3));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(11, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertEquals(game.getSpamDeck().getSpamDeck().size(), game.getSpamDeck().getSpamDeck().size());

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
	}
	//both inside on opposite sides
	@Test
	public void testEIGHT_LB() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new LostBearingsBuilder().build().board()));
		game.setSelectedMap("LostBearings");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(9, 3));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertTrue(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams -1, game.getSpamDeck().getSpamDeck().size());
	}
}
