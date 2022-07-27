package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.protocol.Orientation;
import bb.roborally.server.game.cards.Spam;

import static bb.roborally.protocol.Orientation.*;

public class LaserActivator {
	private Server server;
	private Game game;
	private int figureID;

	public LaserActivator(Server server, Game game,int figureID) {
		this.server = server;
		this.game = game;
		this.figureID = figureID;
	}
	
	public void activate(){
		int originalPosX = game.getRobotList().getRobotByFigureId(figureID).getPosition().getX();
		int originalPosY = game.getRobotList().getRobotByFigureId(figureID).getPosition().getY();
		Orientation robotOrientation = game.getRobotList().getRobotByFigureId(figureID).getRobotOrientation();
		
		boolean shootingEnd = false;

		Spam spam = new Spam();

		while(!shootingEnd){
			if(robotOrientation.equals(LEFT)){
				for (; originalPosX >= 0; originalPosX--) {

					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(LEFT)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;

						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(RIGHT)
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

						shootingEnd = true;
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(TOP)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(BOTTOM)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
						shootingEnd = true;
					}
					if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
				}
			}

			if(robotOrientation.equals(RIGHT)){
				for (; originalPosX <= game.getBoard().getMap().size(); originalPosX++) {

					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(LEFT)) {

						shootingEnd = true;
					}
					if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(RIGHT)
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(TOP)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(BOTTOM)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
						shootingEnd = true;
					}
					if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
				}
			}

			if(robotOrientation.equals(TOP)){
				for (; originalPosY >= 0; originalPosY--) {

					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(LEFT)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(RIGHT)
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(TOP)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(BOTTOM)) {

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
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
				}
			}

			if(robotOrientation.equals(BOTTOM)){
				for (; originalPosY <= game.getBoard().getMap().size(); originalPosY++) {

					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(LEFT)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(RIGHT)
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(TOP)) {

						shootingEnd = true;
					}
					if( (game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))
							&&	 game.getBoard().get(originalPosX,originalPosY).getTile("Wall").getOrientations().equals(BOTTOM)) {

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
					if(game.getBoard().get(originalPosX,originalPosY).hasTile("Antenna")) {
						shootingEnd = true;
					}
					if( (!game.getBoard().get(originalPosX,originalPosY).hasTile("Wall")
							&&	 game.getRobotList().isRobotOnPosition(originalPosX,originalPosY))){

						int playerID = game.getRobotList().getRobotIDByPosition(originalPosX,originalPosY).getFigureId();

						game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
						shootingEnd = true;
						server.broadcast(new DrawDamage(playerID, new String[] {"Spam"}));
					}
				}
			}
		}
	}
}
		