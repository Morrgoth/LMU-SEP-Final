import bb.roborally.map.LostBearingsBuilder;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.BoardLaserActivator;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DizzyHighwayBuilder;
import bb.roborally.server.game.cards.Spam;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ServerBoardModelLaserActivatorTest {
	//DizzyHighway
	//1.robot behind opposite wall on both sides
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

		user1.getRobot().setPosition(new Position(6, 2));
		user2.getRobot().setPosition(new Position(6, 5));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertFalse( user2.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertEquals(game.getSpamDeck().getSpamDeck().size(), game.getSpamDeck().getSpamDeck().size());

	}
	//2. one inside one outside
	@Test
	public void testTWO_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));
		game.getPlayerQueue().add(user1);

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 3));
		game.getPlayerQueue().add(user2);

		Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}

	//2. one inside one outside
	@Test
	public void testTHREE_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));
		game.getPlayerQueue().add(user1);

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 4));
		game.getPlayerQueue().add(user2);

		Spam spam = game.getSpamDeck().drawSpamCard();

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
	//3. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testFOUR_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setMapSelected(true);
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 3));


		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 4));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		//Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());

	}
	//3. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testFIVE_DH() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 4));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertTrue(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		//assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertEquals(numberOfSpams - 1, game.getSpamDeck().getSpamDeck().size());
	}
	//4.both robots outside laser left right
	@Test
	public void testSIX_DH() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(5, 4));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 4));

		Spam spam = game.getSpamDeck().drawSpamCard();

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

	//5.behind both robots outside bottom
	@Test
	public void testSEVEN_DH() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		game.setSelectedMap("DizzyHighway");
		int numberOfSpams = game.getSpamDeck().getSpamDeck().size();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 5));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 6));

		Spam spam = game.getSpamDeck().drawSpamCard();

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertEquals(numberOfSpams -2, game.getSpamDeck().getSpamDeck().size());
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

		Spam spam = game.getSpamDeck().drawSpamCard();

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
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

		Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
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

		Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
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

		Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertTrue(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertFalse(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
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

		Spam spam = game.getSpamDeck().drawSpamCard();

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game,1);
		boardLaserActivator.activate();

		//assertFalse(user1.getProgrammingDeck().getDiscardPile().contains(spam));
		//assertTrue(user2.getProgrammingDeck().getDiscardPile().contains(spam));
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
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

		Spam spam = game.getSpamDeck().drawSpamCard();

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

		Spam spam = game.getSpamDeck().drawSpamCard();

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

		Spam spam = game.getSpamDeck().drawSpamCard();

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

