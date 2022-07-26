package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.Spam;

import static bb.roborally.server.game.Orientation.*;


/*
		1.get shooting robot
		2.get robot orientation
		3.iterate over fields in orientation until (antenna || wall ) or robot first.
		4. after first aim - break;
*/


public class RobotLaserActivator {
	private Server server;
	private Game game;

	public RobotLaserActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}

	public void activate(){
		Animation animation = new Animation("Laser");
		server.broadcast(animation);

		for (User user : game.getPlayerQueue().getUsers()) {
			int originalPosX = game.getRobotList().getRobotByFigureId(user.getClientID()).getPosition().getX();
			int originalPosY = game.getRobotList().getRobotByFigureId(user.getClientID()).getPosition().getY();
			Orientation robotOrientation = game.getRobotList().getRobotByFigureId(user.getClientID()).getRobotOrientation();

			boolean shootingEnd = false;

			Spam spam = new Spam();

			while(!shootingEnd){
				if(robotOrientation.equals(LEFT)){
					for (; originalPosX >= 0; originalPosX--) {

						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(LEFT)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;

							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(RIGHT)
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

							shootingEnd = true;
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(TOP)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(BOTTOM)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
							shootingEnd = true;
						}
						if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
					}
				}

				if(robotOrientation.equals(RIGHT)){
					for (; originalPosX <= game.getBoard().getMap().size(); originalPosX++) {

						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(LEFT)) {

							shootingEnd = true;
						}
						if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(RIGHT)
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(TOP)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(BOTTOM)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
							shootingEnd = true;
						}
						if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
					}
				}

				if(robotOrientation.equals(TOP)){
					for (; originalPosY >= 0; originalPosY--) {

						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(LEFT)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(RIGHT)
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(TOP)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(BOTTOM)) {

							shootingEnd = true;
						}
						if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
							shootingEnd = true;
						}
						if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
					}
				}

				if(robotOrientation.equals(BOTTOM)){
					for (; originalPosY <= game.getBoard().getMap().size(); originalPosY++) {

						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(LEFT)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(RIGHT)
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(TOP)) {

							shootingEnd = true;
						}
						if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
								&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().contains(BOTTOM)) {

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
						if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
							shootingEnd = true;
						}
						if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
								&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

							int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

							game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
							shootingEnd = true;
							server.broadcast(new DrawDamage(playerID, new String[]{"Spam"}));
						}
					}
				}
			}
		}
	}
}
