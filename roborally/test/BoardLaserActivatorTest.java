import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.activation.BoardLaserActivator;
import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.map.DizzyHighway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.*;
import static jdk.internal.org.jline.utils.InfoCmp.Capability.user1;
import static org.junit.jupiter.api.Assertions.*;

public class BoardLaserActivatorTest {
	private static Server server;
	private static Game game;

	@BeforeAll
	public static void init(){
		server = new Server();
		game = server.getGame();
		game.setBoard(new Board(DizzyHighway.buildDizzyHighway()));
	}

	@Test
	public void testBoardLaserShootAntenna() throws IOException{

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(1, 3));
		game.getPlayerQueue().add(user1);

		game.getBoard().getWall().get(0).setPosition(0,3);
		game.getBoard().getAntenna().setPosition(2,3);
		game.getBoard().getBoardLaser().get(0).setPosition(4,3);
		game.getBoard().getBoardLaser().get(0).getTile("Laser").setCount(1);

		ArrayList<Orientation> orientationLeft = new ArrayList<>();
		orientationLeft.add(LEFT);
		game.getBoard().getBoardLaser().get(0).getTile("Laser").setOrientations(orientationLeft);
		ActivationPhaseHandler.getRegister();
		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);

		while(game.getBoard().getBoardLaser().get(0).getTile("Laser").getCount() == ActivationPhaseHandler.getRegister()) {
			boardLaserActivator.activate();
		}

		assertTrue(boardLaserActivator.isShootingEnded);


		/*
		game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
		isShootingEnded = true; 	ok
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
		 */
	}

	@Test
	public void testBoardLaserShootWallOnly() throws IOException{

		User user1 = new User(0);
		user1.setName("user1");
		user1.setRobot(game.getRobotList().getRobotByFigureId(1));
		user1.getRobot().setPosition(new Position(1, 3));
		game.getPlayerQueue().add(user1);

		game.getBoard().getWall().get(0).setPosition(3,3);
		game.getBoard().getAntenna().setPosition(0,3);
		game.getBoard().getBoardLaser().get(0).setPosition(4,3);

		ArrayList<Orientation> orientationLeft = new ArrayList<>();
		orientationLeft.add(LEFT);
		game.getBoard().getBoardLaser().get(0).getTile("Laser").setOrientations(orientationLeft);

		ActivationPhaseHandler.getRegister();
		BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server,game);

		for(int i = 0; i <= 3; i++){
			boardLaserActivator.activate();
		}

		/*
		Assertions.assertAll(
				() -> assertTrue(boardLaserActivator.isShootingEnded),
				()-> 	assertDoesNotThrow((Executable) new RuntimeException()),
				()->  	assertNotEquals (1, new DrawDamage(0, "Spam")),
				() -> 	assertNotEquals (2, new DrawDamage(0, "Spam")),
				() -> 	assertNotEquals (3, new DrawDamage(0, "Spam")),
				() ->	assertNotEquals (4, new DrawDamage(0, "Spam")),
				() ->	assertNotEquals (5, new DrawDamage(0, "Spam"))
		);
		 */



		assertTrue(boardLaserActivator.isShootingEnded);
		assertDoesNotThrow((Executable) new RuntimeException());
		assertNotEquals (1, new DrawDamage(0, "Spam"));
		assertNotEquals (2, new DrawDamage(0, "Spam"));
		assertNotEquals (3, new DrawDamage(0, "Spam"));
		assertNotEquals (4, new DrawDamage(0, "Spam"));
		assertNotEquals (5, new DrawDamage(0, "Spam"));


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
