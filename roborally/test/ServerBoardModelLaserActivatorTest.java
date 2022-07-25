import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.BoardLaserActivator;
import bb.roborally.server.game.board.ServerBoard;
import bb.roborally.map.DizzyHighwayBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.LEFT;
import static org.junit.jupiter.api.Assertions.*;

public class ServerBoardModelLaserActivatorTest {
	//1.robot behind opposite wall
	@Test
	public void testRobotInsideLaser() {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 4));


		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertTrue(boardLaserActivator.isShootingEnded);
		assertEquals(0, user1.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());

		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertNotEquals("Spam", user2.getProgrammingDeck().getDiscardPile().get(0).getName());
	}
	//2.3.one robot inside; second robot besides doesn't get shot
	@Test
	public void testRobotInsideOutsideLaser() {

		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 3));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 4));


		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertTrue(boardLaserActivator.isShootingEnded);
		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(0).getName());
	}
	//4. both robots inside laser; one gets shot ; second doesn't
	@Test
	public void testRobotTwoRobotsInsideLaserTest(){
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

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(21));

		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertTrue(boardLaserActivator.isShootingEnded);


	}


	@Test
	public void testRobotOutside() {
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
		user2.getRobot().setPosition(new Position(7, 4));
		game.getPlayerQueue().add(user2);

		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);
		boardLaserActivator.activate();

		assertTrue(boardLaserActivator.isShootingEnded);
	}
	//5.one r.inside shot; one outside not shot
	@Test
	public void testBoardLaserShootWallOnly() {
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
		user2.getRobot().setPosition(new Position(6, 2));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		ActivationPhaseHandler.getRegister();
		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);

		for(int i = 0; i <= 3; i++){
			boardLaserActivator.activate();
		}

		assertEquals(1, user1.getProgrammingDeck().getDiscardPile().size());
		assertEquals("Spam", user1.getProgrammingDeck().getDiscardPile().get(21));

		assertEquals(0, user2.getProgrammingDeck().getDiscardPile().size());
		assertTrue(boardLaserActivator.isShootingEnded);
	}

	//robot outside; robot outside
	@Test
	public void testBoardLaserShootWallOnlyBothOutside() {
		Server server = new Server();
		Game game = server.getGame();
		game.setBoard(new ServerBoard(new DizzyHighwayBuilder().build().board()));

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(6, 2));

		User user2 = new User(1);
		user2.setName("user2");
		user2.setRobot(game.getRobotList().getRobotByFigureId(2));
		user2.getRobot().setPosition(new Position(7, 4));

		game.getPlayerQueue().add(user1);
		game.getPlayerQueue().add(user2);

		ActivationPhaseHandler.getRegister();
		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);

		for(int i = 0; i <= 3; i++){
			boardLaserActivator.activate();
		}
		assertTrue(boardLaserActivator.isShootingEnded);
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
