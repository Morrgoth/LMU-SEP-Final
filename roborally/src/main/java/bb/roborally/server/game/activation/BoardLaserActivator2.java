package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.map.tiles.Laser;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;
import bb.roborally.server.game.cards.Spam;

import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.LEFT;
import static bb.roborally.server.game.Orientation.RIGHT;

public class BoardLaserActivator2 {

	private Server server;
	private Game game;
	private int playerID;


	//f[r test am Ende l;schen
	int register;
	//f[r test am Ende l;schen

	public BoardLaserActivator2(Server server, Game game, int register) {
		this.server = server;
		this.game = game;
		this.register = register;
	}

	//Case LEFT
	public int getClientIDMaxX(int laserPosX,int laserPosY) {

		int maxRobotX = 0;

		for (User user : game.getPlayerQueue().getUsers()) {

			int currentRobotX = user.getRobot().getPosition().getX();
			int currentRobotY = user.getRobot().getPosition().getY();
			int currentPlayerID = user.getClientID();

			if (laserPosY == currentRobotY && currentRobotX <= laserPosX && maxRobotX < currentRobotX) {
				maxRobotX = currentRobotX;
				playerID = currentPlayerID;
				boolean isCase = true;
			}
			return  maxRobotX;
		}
		return playerID;
	}
	//Case RIGHT
	public int getClientIDMinX(int laserPosX,int laserPosY) {

		int minRobotX = 15;

		for (User user : game.getPlayerQueue().getUsers()) {

			int currentRobotX = user.getRobot().getPosition().getX();
			int currentRobotY = user.getRobot().getPosition().getY();
			int currentPlayerID = user.getClientID();

			if (laserPosY == currentRobotY && currentRobotX >= laserPosX && minRobotX > currentRobotX) {
				minRobotX = currentRobotX;
				playerID = currentPlayerID;
				boolean isCase = true;
			}
			return  minRobotX;
		}
		return playerID;
	}
	//Case TOP
	public int getClientIDMaxY(int laserPosX,int laserPosY) {

		int maxRobotY = 0;

		for (User user : game.getPlayerQueue().getUsers()) {

			int currentRobotX = user.getRobot().getPosition().getX();
			int currentRobotY = user.getRobot().getPosition().getY();
			int currentPlayerID = user.getClientID();

			if (laserPosX == currentRobotX && currentRobotY <= laserPosY && maxRobotY < currentRobotY) {
				maxRobotY = currentRobotX;
				playerID = currentPlayerID;
				boolean isCase = true;
			}
			return  maxRobotY;
		}
		return playerID;
	}
	//Case BOTTOM
	public int getClientIDMinY(int laserPosX,int laserPosY) {

		int minRobotY = 15;

		for (User user : game.getPlayerQueue().getUsers()) {

			int currentRobotX = user.getRobot().getPosition().getX();
			int currentRobotY = user.getRobot().getPosition().getY();
			int currentPlayerID = user.getClientID();

			if (laserPosX == currentRobotX && currentRobotY >= laserPosY && minRobotY > currentRobotY) {
				minRobotY = currentRobotX;
				playerID = currentPlayerID;
				boolean isCase = true;
			}
			return  minRobotY;
		}
		return playerID;
	}

	public void activate() {
		Animation animation = new Animation("Laser");
		server.broadcast(animation);

		ArrayList<ServerCell> laserList = game.getBoard().getBoardLaser();

		for (ServerCell laserCell : laserList) {
			int number = ((Laser) laserCell.getTile("Laser")).getCount();
			int register = ActivationPhaseHandler.getRegister();


			if (number == register) {
				int laserPosX = laserCell.getPosition().getX();
				int laserPosY = laserCell.getPosition().getY();

				Spam spam = game.getSpamDeck().drawSpamCard();

				if (laserCell.getTile("Laser").getOrientations().contains(LEFT)) {
					boolean isCase = false;
					getClientIDMaxX(laserPosX,laserPosY);
					if(isCase){

					}
					else{
						break;
					}
				}
				if (laserCell.getTile("Laser").getOrientations().contains(RIGHT)) {

				}
			}
		}
	}
}
