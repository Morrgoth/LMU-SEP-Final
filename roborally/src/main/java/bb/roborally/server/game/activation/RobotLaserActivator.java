package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.protocol.map.tiles.Laser;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.Spam;

import java.util.ArrayList;
import java.util.Collections;


import static bb.roborally.server.game.Orientation.*;

public class RobotLaserActivator {
	private Server server;
	private Game game;
	private ArrayList<Integer> newSortX = new ArrayList<>();
	private ArrayList<Integer> newSortY = new ArrayList<>();


	int register;

	public RobotLaserActivator(Server server, Game game, int register) {
		this.server = server;
		this.game = game;
		this.register = ActivationPhaseHandler.getRegister();
	}

	public void activate() {
		Animation animation = new Animation("Laser");
		server.broadcast(animation);

		boolean isShot;
		for (User user: game.getPlayerQueue().getUsers()) {
			newSortX.clear();
			newSortY.clear();

				int robotPosX = user.getRobot().getPosition().getX();
				int robotPosY = user.getRobot().getPosition().getY();

				isShot = false;

				if (user.getRobot().getRobotOrientation() == LEFT) {
					for (int robotPositionXNew = user.getRobot().getPosition().getX() - 1; robotPositionXNew >= 0; robotPositionXNew--) {
						for (User otherUser : game.getPlayerQueue().getUsers()) {
							if (robotPosY == otherUser.getRobot().getPosition().getY() && robotPositionXNew >= otherUser.getRobot().getPosition().getX()) {

								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if (game.getBoard().get(robotPositionXNew, robotPosY).hasTile("Wall")
										&& game.getBoard().get(robotPositionXNew, robotPosY).getTile("Wall").getOrientations().contains(RIGHT)
										&& otherUser.getRobot().getPosition().getX() == robotPosX
										&& otherUser.getRobot().getPosition().getY() == robotPosY) {


									isShot = true;
									newSortX.add(otherUser.getRobot().getPosition().getX());
									break;
								}


								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(robotPositionXNew, robotPosY).hasTile("Wall")
										&& (game.getBoard().get(robotPositionXNew, robotPosY).getTile("Wall").getOrientations().contains(LEFT) && otherUser.getRobot().getPosition().getX() >= robotPositionXNew))) {

									isShot = true;
									newSortX.add(otherUser.getRobot().getPosition().getX());
									break;
								}

								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(robotPositionXNew, robotPosY).hasTile("Wall"))) {
									ArrayList<Boolean> hasWall = new ArrayList<>();


									for (int i = otherUser.getRobot().getPosition().getX() + 1; i < robotPosX; i++) {
										if (game.getBoard().get(i, robotPosY).hasTile("Wall") && game.getBoard().get(i, robotPosY).getTile("Wall").getOrientations().get(0) == RIGHT){
											hasWall.add(true);
										}else if(game.getBoard().get(i, robotPosY).hasTile("Wall") && game.getBoard().get(i, robotPosY).getTile("Wall").getOrientations().get(0) == LEFT) {
											hasWall.add(true);
										}else{
											hasWall.add(false);
										}
									}

									if(game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == RIGHT){
										hasWall.add(true);
									}

									if(game.getBoard().get(robotPosX, robotPosY).hasTile("Wall") && game.getBoard().get(robotPosX, robotPosY).getTile("Wall").getOrientations().get(0) == LEFT){
										hasWall.add(true);
									}

									if (!(hasWall.contains(true))){
										isShot = true;
										newSortX.add(otherUser.getRobot().getPosition().getX());

									}

								}
							}
						}

						if(newSortX.size() != 0){
							Collections.sort(newSortX);
							int max = newSortX.get(newSortX.size()-1);
							for (User otherUser: game.getPlayerQueue().getUsers()) {
								if (otherUser.getRobot().getPosition().getX() == max && otherUser.getRobot().getPosition().getY() == robotPosY) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									otherUser.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(otherUser.getClientID(), "Spam");
									server.broadcast(drawDamage);
								}
							}
						}
						if(isShot){
							break;
						}
					}

				}
				if (user.getRobot().getRobotOrientation() == RIGHT) {
					for (int robotPosXNew = user.getRobot().getPosition().getX() + 1; robotPosXNew <= 12; robotPosXNew++) {
						for (User otherUser : game.getPlayerQueue().getUsers()) {
							if (robotPosY == otherUser.getRobot().getPosition().getY() && robotPosXNew <= otherUser.getRobot().getPosition().getX()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if (game.getBoard().get(robotPosXNew, robotPosY).hasTile("Wall")
										&& game.getBoard().get(robotPosXNew, robotPosY).getTile("Wall").getOrientations().contains(LEFT)
										&& otherUser.getRobot().getPosition().getX() == robotPosX
										&& otherUser.getRobot().getPosition().getY() == robotPosY) {


									newSortX.add(otherUser.getRobot().getPosition().getX());
									break;
								}

								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(robotPosXNew, robotPosY).hasTile("Wall")
										&& (game.getBoard().get(robotPosXNew, robotPosY).getTile("Wall").getOrientations().contains(RIGHT) && otherUser.getRobot().getPosition().getX() <=  robotPosXNew))) {

									isShot = true;
									newSortX.add(user.getRobot().getPosition().getX());
									break;
								}

								//only Antenna- stop laser
								if (game.getBoard().get(robotPosXNew, robotPosY).hasTile("Antenna")) {
									break;
								}

								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(robotPosXNew, robotPosY).hasTile("Wall"))) {
									ArrayList<Boolean> hasWall = new ArrayList<>();

									for (int i = otherUser.getRobot().getPosition().getX() - 1; i > robotPosX; i--) {
										if (game.getBoard().get(i, robotPosY).hasTile("Wall") && game.getBoard().get(i, robotPosY).getTile("Wall").getOrientations().get(0) == LEFT) {
											hasWall.add(true);
										}else if (game.getBoard().get(i, robotPosY).hasTile("Wall") && game.getBoard().get(i, robotPosY).getTile("Wall").getOrientations().get(0) == RIGHT) {
											hasWall.add(true);
										}else{
											hasWall.add(false);
										}
									}
									if(game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == LEFT){
										hasWall.add(true);
									}

									if(game.getBoard().get(robotPosX, robotPosY).hasTile("Wall") && game.getBoard().get(robotPosX, robotPosY).getTile("Wall").getOrientations().get(0) == RIGHT){
										hasWall.add(true);
									}
									if (!(hasWall.contains(true))) {
										isShot = true;
										newSortX.add(otherUser.getRobot().getPosition().getX());
									}
								}
							}
						}


						if(newSortX.size() != 0){
							Collections.sort(newSortX);
							int min = newSortX.get(0);
							for (User otherUser : game.getPlayerQueue().getUsers()) {
								if (otherUser.getRobot().getPosition().getX() == min && otherUser.getRobot().getPosition().getY() == robotPosY) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									otherUser.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(otherUser.getClientID(), "Spam");
									server.broadcast(drawDamage);
								}
							}
						}
						if(isShot){
							break;
						}
					}

				}
				if (user.getRobot().getRobotOrientation() == TOP) {
					for (int robotPosYNew = user.getRobot().getPosition().getY() -1 ; robotPosYNew >= 0; robotPosYNew--) {
						for (User otherUser : game.getPlayerQueue().getUsers()) {
							if (robotPosX == otherUser.getRobot().getPosition().getX() && robotPosYNew >= otherUser.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if (game.getBoard().get(robotPosX, robotPosYNew).hasTile("Wall")
										&& game.getBoard().get(robotPosX, robotPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)
										&& otherUser.getRobot().getPosition().getY() == robotPosY) {

									isShot = true;
									newSortY.add(otherUser.getRobot().getPosition().getY());
									break;
								}

								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(robotPosX, robotPosYNew).hasTile("Wall")
										&& (game.getBoard().get(robotPosX, robotPosYNew).getTile("Wall").getOrientations().contains(TOP) && otherUser.getRobot().getPosition().getY() >= robotPosYNew))) {

									isShot = true;
									newSortY.add(otherUser.getRobot().getPosition().getY());
									break;
								}

								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(robotPosX, robotPosYNew).hasTile("Wall"))) {
									ArrayList<Boolean> hasWall = new ArrayList<>();

									for (int i = otherUser.getRobot().getPosition().getY() + 1; i < robotPosY; i++) {
										if (game.getBoard().get(robotPosX, i).hasTile("Wall") && game.getBoard().get(robotPosX, i).getTile("Wall").getOrientations().get(0) == BOTTOM) {
											hasWall.add(true);
										}else if(game.getBoard().get(robotPosX, i).hasTile("Wall") && game.getBoard().get(robotPosX, i).getTile("Wall").getOrientations().get(0) == TOP){
											hasWall.add(true);
										}else{
											hasWall.add(false);
										}
									}

									if(game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == BOTTOM){
										hasWall.add(true);
									}

									if(game.getBoard().get(robotPosX, robotPosY).hasTile("Wall") && game.getBoard().get(robotPosX, robotPosY).getTile("Wall").getOrientations().get(0) == TOP){
										hasWall.add(true);
									}
									if (!(hasWall.contains(true))){
										isShot = true;
										newSortY.add(otherUser.getRobot().getPosition().getY());
									}

								}
							}

						}


						if (newSortY.size() != 0) {
							Collections.sort(newSortY);
							int max = newSortY.get(newSortY.size()-1);
							for (User otherUser : game.getPlayerQueue().getUsers()) {
								if (otherUser.getRobot().getPosition().getY() == max && otherUser.getRobot().getPosition().getX() == robotPosX) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									otherUser.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(otherUser.getClientID(), "Spam");
									server.broadcast(drawDamage);
								}
							}
						}
						if(isShot){
							break;
						}
					}

				}


				if (user.getRobot().getRobotOrientation() == BOTTOM) {
					for (int robotPosYNew = user.getRobot().getPosition().getY() + 1; robotPosYNew <= 9; robotPosYNew++) {
						for (User otherUser : game.getPlayerQueue().getUsers()) {
							if (robotPosX == otherUser.getRobot().getPosition().getX() && robotPosYNew <= otherUser.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if (game.getBoard().get(robotPosX, robotPosYNew).hasTile("Wall")
										&& game.getBoard().get(robotPosX, robotPosYNew).getTile("Wall").getOrientations().contains(TOP)
										&& otherUser.getRobot().getPosition().getY() == robotPosY) {

									isShot = true;
									newSortY.add(user.getRobot().getPosition().getY());
									break;
								}

								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(robotPosX, robotPosYNew).hasTile("Wall")
										&& (game.getBoard().get(robotPosX, robotPosYNew).getTile("Wall").getOrientations().contains(BOTTOM) && otherUser.getRobot().getPosition().getY() <= robotPosYNew))) {

									isShot = true;
									newSortY.add(otherUser.getRobot().getPosition().getY());
									break;
								}

								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(robotPosX, robotPosYNew).hasTile("Wall"))) {
									ArrayList<Boolean> hasWall = new ArrayList<>();

									for (int i = otherUser.getRobot().getPosition().getY() - 1; i > robotPosY; i--) {
										if (game.getBoard().get(robotPosX, i).hasTile("Wall") && game.getBoard().get(robotPosX, i).getTile("Wall").getOrientations().get(0) == TOP) {
											hasWall.add(true);
										} else if (game.getBoard().get(robotPosX, i).hasTile("Wall") && game.getBoard().get(robotPosX, i).getTile("Wall").getOrientations().get(0) == BOTTOM) {
											hasWall.add(true);
										} else {
											hasWall.add(false);
										}
									}
									if (game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(otherUser.getRobot().getPosition().getX(), otherUser.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == TOP) {
										hasWall.add(true);
									}

									if (game.getBoard().get(robotPosX, robotPosY).hasTile("Wall") && game.getBoard().get(robotPosX, robotPosY).getTile("Wall").getOrientations().get(0) == BOTTOM) {
										hasWall.add(true);
									}
									if (!(hasWall.contains(true))) {
										isShot = true;
										newSortY.add(otherUser.getRobot().getPosition().getY());
									}

								}

							}
						}

						if(newSortY.size() != 0){
							Collections.sort(newSortY);
							int min = newSortY.get(0);
							for (User otherUser : game.getPlayerQueue().getUsers()) {
								if (otherUser.getRobot().getPosition().getY() == min && otherUser.getRobot().getPosition().getX() == robotPosX) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									otherUser.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(otherUser.getClientID(), "Spam");
									server.broadcast(drawDamage);
								}
							}
							if(isShot){
								break;
							}
						}
					}
				}
			}
		}
	}

