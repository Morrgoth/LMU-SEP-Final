import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.BoardLaserActivator2;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DizzyHighwayBuilder;
import bb.roborally.server.game.cards.Spam;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ServerBoardModelLaserActivatorTest {

	//1.robot behind opposite wall on both sides
	@Test
	public void testONE() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		User user1 = new User(0);
		User user2 = new User(1);
		user1.setName("user1");
		user2.setName("user2");

		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 5));

		Spam spam = game.getSpamDeck().drawSpamCard();

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator2 boardLaserActivator = new BoardLaserActivator2(server,game,1);
		boardLaserActivator.activate();

		assertNotEquals(1, user1.getProgrammingDeck().getDiscardPile().contains(spam));
		assertNotEquals(1, user2.getProgrammingDeck().getDiscardPile().contains(spam));

	}

	//2. one inside one outside
	@Test
	public void testFOUR() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

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

		BoardLaserActivator2 boardLaserActivator = new BoardLaserActivator2(server,game,1);
		boardLaserActivator.activate();

		assertNotEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user2.getProgrammingDeck().getDiscardPile().contains(spam));
	}
	//3. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testRobotTHREE() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

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

		BoardLaserActivator2 boardLaserActivator = new BoardLaserActivator2(server,game,1);
		boardLaserActivator.activate();

		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().contains(spam));
	}
	//4.both robots outside laser left right
	@Test
	public void testFOUR() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

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

		BoardLaserActivator2 boardLaserActivator = new BoardLaserActivator2(server,game,1);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
	}

	//5.behind

	//robot outside; robot outside
	@Test
	public void testFIVE() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(5, 3));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		ActivationPhaseHandler.getRegister();
		BoardLaserActivator2 boardLaserActivator = new BoardLaserActivator2(server,game,1);

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());

	}

	@Test
	public void testSIX() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6,5));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6,6));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		ActivationPhaseHandler.getRegister();
		BoardLaserActivator2 boardLaserActivator = new BoardLaserActivator2(server,game,1);

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());

		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
	}


}
/*
create map
create user
set wall antenna robot position

laser antenna						isShootingEnd true + no drawDamage
laser wall same orientation			isShootingEnd true +  drawDamage
laser wall opposite orientation		isShootingEnd true + no drawDamage
laser wall both other or.			isShootingEnd true +  drawDamage

laser robot 						isShootingEnd true +  drawDamage
laser wall no robot					isShootingEnd true +  no drawDamage

 */
