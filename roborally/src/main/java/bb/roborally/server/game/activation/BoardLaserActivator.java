package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.protocol.map.Cell;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;
import bb.roborally.server.game.cards.Spam;
import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.*;

public class BoardLaserActivator {
	private Server server;
	private Game game;
	public boolean isShootingEnded;

	public BoardLaserActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}
	public void activate() throws IOException {
		Animation animation = new Animation("Laser");

			server.broadcast(animation);

		ArrayList<ServerCell> laserList = game.getBoard().getBoardLaser();
		for (ServerCell laserCell : laserList) {
			//for (Tile tile: laserCell) {
				if (laserCell.hasTile("Laser")) {
					if (laserCell.getTile("Laser").getCount() == ActivationPhaseHandler.getRegister()) {

						int laserPosX = laserCell.getPosition().getX();
						int laserPosY = laserCell.getPosition().getY();

						int laserCase = 0;
						Spam spam = (Spam) game.getSpamDeck().drawSpamCard();
						isShootingEnded = false;

						while(!isShootingEnded){

							if(laserCell.getTile("Laser").getOrientations().contains(LEFT)){
								for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew >= 0; laserPosXNew--) {
									
									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() == laserPosY)
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(LEFT)) {

										
											isShootingEnded = true;
											

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(RIGHT)
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() == laserPosY) {


											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&	 user.getRobot().getPosition().getY() == laserPosY)
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(TOP)) {

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;
											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&	 user.getRobot().getPosition().getY() == laserPosY)
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(BOTTOM)) {

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									//only Antenna
									if(game.getBoard().get(laserPosXNew,laserPosY).hasTile("Antenna")) {
										isShootingEnded = true;
									}
									//only Robot
									for(User user: game.getPlayerQueue().getUsers()){
										if( (!game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() == laserPosY)){

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									//only Wall
									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() != laserPosXNew
												||   user.getRobot().getPosition().getY()  != laserPosY)){

											isShootingEnded = true;
										}
									}

								}
							}

							if(laserCell.getTile("Laser").getOrientations().contains(RIGHT)){
								for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew < game.getBoard().getMap().size(); laserPosXNew++) {
									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
										 		&&   user.getRobot().getPosition().getY() == laserPosY)
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(LEFT)) {

											isShootingEnded = true;
										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(RIGHT)
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() == laserPosY) {

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() == laserPosY)
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(TOP)) {

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() ==  laserPosY)
												&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().contains(BOTTOM)) {

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									if(game.getBoard().get(laserPosXNew,laserPosY).hasTile("Antenna")) {
										isShootingEnded = true;
									}

									for (User user: game.getPlayerQueue().getUsers()){
										if( (!game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosXNew
												&&   user.getRobot().getPosition().getY() == laserPosY)){

											

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;
											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									for (User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() != laserPosXNew
												||   user.getRobot().getPosition().getY() != laserPosY)){

											isShootingEnded = true;
										}
									}

								}
							}

							if(laserCell.getTile("Laser").getOrientations().contains(TOP)){
								for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew >= 0; laserPosYNew--) {
									for (User user: game.getPlayerQueue().getUsers()){
										if((game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&	 user.getRobot().getPosition().getY() == laserPosYNew)
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(LEFT)) {

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}


									for(User user: game.getPlayerQueue().getUsers()){
										if( game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT)
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&   user.getRobot().getPosition().getY() == laserPosYNew) {

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

											DrawDamage drawDamage = new DrawDamage(user.getClientID(),"Spam");
											server.broadcast(new DrawDamage(user.getClientID(), "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if((game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&   user.getRobot().getPosition().getY() == laserPosYNew)
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(TOP)) {

											//int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId() - 1;

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;
											DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
											server.broadcast(drawDamage);

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
										        &&   user.getRobot().getPosition().getY() == laserPosYNew)
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)) {

											isShootingEnded = true;
										}
									}

									if(game.getBoard().get(laserPosX,laserPosYNew).hasTile("Antenna")) {
										isShootingEnded = true;
									}
									for(User user: game.getPlayerQueue().getUsers()){
										if((!game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&   user.getRobot().getPosition().getY() == laserPosYNew)){

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

												server.broadcast(new DrawDamage(playerID, "Spam"));

										}
									}


									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() != laserPosX
												||   user.getRobot().getPosition().getY() != laserPosYNew)){

											isShootingEnded = true;
										}
									}

								}
							}

							if(laserCell.getTile("Laser").getOrientations().contains(BOTTOM)){
								for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew < 10; laserPosYNew++) {

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
										        &&   user.getRobot().getPosition().getY() == laserPosYNew)
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(LEFT)) {

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

												server.broadcast(new DrawDamage(playerID, "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT)
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&   user.getRobot().getPosition().getY() == laserPosYNew) {

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

												server.broadcast(new DrawDamage(playerID, "Spam"));

										}
									}

									for (User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
										        &&   user.getRobot().getPosition().getY() == laserPosYNew)
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(TOP)) {

											isShootingEnded = true;
										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&   user.getRobot().getPosition().getY() == laserPosYNew)
												&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)) {

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

												server.broadcast(new DrawDamage(playerID, "Spam"));

										}
									}

									if(game.getBoard().get(laserPosX,laserPosYNew).hasTile("Antenna")) {
										isShootingEnded = true;
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (!game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() == laserPosX
												&&   user.getRobot().getPosition().getY() == laserPosYNew)){

											int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

											game.getPlayerQueue().getUsers().get(user.getClientID()).getProgrammingDeck().addCard(spam, true);
											isShootingEnded = true;

												server.broadcast(new DrawDamage(playerID, "Spam"));

										}
									}

									for(User user: game.getPlayerQueue().getUsers()){
										if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
												&&	 user.getRobot().getPosition().getX() != laserPosX
												||   user.getRobot().getPosition().getY() != laserPosYNew)){

											isShootingEnded = true;
										}
									}

								}
							}
						}
					}
				}
			//}
		}
	}
}

/*
1.get all BL
2.get into BL-cells
3.get into BL-cells-tiles
4.get BL Position and Orientation
5.get selectedMap
6.iterate according to position and orientation e.g LEFT x--  RIGHT x++ TOP y-- BOTTOM y++
7. 	get board and each tile at position x/y ;

	if tile at position (LEFT/RIGHT) new x , same y ; (TOP/BOTTOM) same x, new y;
	contains antenna break;
	contains wall and robot opposite wall & laser orientation -break;
	contains wall and robot same wall & laser orientation - shoot ;
	contains robot shoot;
8.	new Spam card;
	create new Message - DrawDamage.java;
 */
