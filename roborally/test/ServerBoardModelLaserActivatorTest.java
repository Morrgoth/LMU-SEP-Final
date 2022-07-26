import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.BoardLaserActivator;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DizzyHighwayBuilder;
import bb.roborally.server.game.cards.Spam;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.LEFT;
import static org.junit.jupiter.api.Assertions.*;

public class ServerBoardModelLaserActivatorTest {
	//1.robot behind opposite wall
	@Test
	public void testONE() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(server, game);
		activationPhaseHandler.start();
		activationPhaseHandler.setRegister(1);

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 5));


		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertTrue(boardLaserActivator.isShootingEnded);
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());

	}
	//2.3.one robot inside; second robot besides doesn't get shot
	@Test
	public void testTWO() throws IOException {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(server, game);
		activationPhaseHandler.start();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));

		/*User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 4));*/

		//ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(server, game);
		ActivationPhaseHandler.setRegister(1);


		game.getPlayerQueue().add(user1);
		//game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertTrue(boardLaserActivator.isShootingEnded);
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());

	}
	//4. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testRobotTHREE() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		//ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(server, game);
		//activationPhaseHandler.start();

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 3));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		//assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		//assertThrows(IndexOutOfBoundsException.class, () -> boardLaserActivator.activate());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
		assertTrue(boardLaserActivator.isShootingEnded);


	}


	@Test
	public void testFOUR() throws IOException {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));
		ActivationPhaseHandler activationPhaseHandler = new ActivationPhaseHandler(server, game);
		activationPhaseHandler.start();


		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 3));
		game.getPlayerQueue().add(user1);

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(6, 4));
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(1, user2.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

		assertTrue(boardLaserActivator.isShootingEnded);
	}
	//5.one r.inside shot; one outside not shot

	//robot outside; robot outside
	@Test
	public void testFIVE() {
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
		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);

		assertTrue(boardLaserActivator.isShootingEnded);

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());

	}

	@Test
	public void testSIX(){
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
		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);

		assertTrue(boardLaserActivator.isShootingEnded);

		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());

		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		;
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
