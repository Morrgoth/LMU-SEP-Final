package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;

import java.io.IOException;

public class RobotLaserActivator {
	private Server server;
	private Game game;

	public RobotLaserActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}

	public void activate(){
		Animation animation = new Animation("Laser");
		try {
			server.broadcast(animation);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


		int counterRobot = game.getPlayerQueue().getUsers().size();
		int robotPositionX = game.getPlayerQueue().getUsers().get(counterRobot).getRobot().getPosition().getX();
		int robotPositionY = game.getPlayerQueue().getUsers().get(counterRobot).getRobot().getPosition().getY();

		/*
		1.get shooting robot
		2.get robot orientation
		3.iterate over fields in orientation until (antenna || wall ) or robot first.
		4. after first aim - break;
		 */
	}
}
