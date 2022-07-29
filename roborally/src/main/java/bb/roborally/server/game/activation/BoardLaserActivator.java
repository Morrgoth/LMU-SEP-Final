package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.protocol.map.tiles.Laser;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;
import bb.roborally.server.game.cards.Spam;
import java.util.ArrayList;
import java.util.Collections;


import static bb.roborally.protocol.Orientation.*;

/**
 * Class handles the functionality of the BoardLasers
 * iteration from the laser firing point one step further until the end of the board
 * if any Robot is shot check if there is a block
 * defining user that takes spam by min or max of the distance from Robot to the Laser
 * @author Philipp Keyzman
 * @author Veronika Heckel
 */
public class BoardLaserActivator {
	private Server server;
	private Game game;
	private ArrayList<Integer> newSortX = new ArrayList<>();
	private ArrayList<Integer> newSortY = new ArrayList<>();

	int register;

	public BoardLaserActivator(Server server, Game game, int register) {
		this.server = server;
		this.game = game;
		this.register = register;
	}

	public void activate() {
		Animation animation = new Animation("Laser");
		server.broadcast(animation);

		boolean isShot;
		ArrayList<ServerCell> laserList = game.getBoard().getBoardLaser();
		for (ServerCell laserCell : laserList) {											//storing every LaserCell of the Board in a List
			newSortX.clear();
			newSortY.clear();
			int number = ((Laser) laserCell.getTile("Laser")).getCount();
			int register = ActivationPhaseHandler.getRegister();

			if (number == register) {

				int laserPosX = laserCell.getPosition().getX();
				int laserPosY = laserCell.getPosition().getY();

				isShot = false;

				if (laserCell.getTile("Laser").getOrientations().contains(LEFT)) {											//iterating in every direction of the Laser
					for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew >= 0; laserPosXNew--) {				//iteration in direction of Laser over every Tile in front of it - one step per iteration
						for (User user : game.getPlayerQueue().getUsers()) {
								if (laserPosY == user.getRobot().getPosition().getY() && laserPosXNew >= user.getRobot().getPosition().getX()) {

									//special case: laser on same tile as robot and opposite direction of the wall --> isShot
									if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosY) {


										isShot = true;
										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}


									//all other wall directions on other tiles --> isShot
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT) && user.getRobot().getPosition().getX() >= laserPosXNew))) {

										isShot = true;
										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}

									//only Robot shoot laser, stop laser
									if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall"))) {
										ArrayList<Boolean> hasWall = new ArrayList<>();


										for (int i = user.getRobot().getPosition().getX() + 1; i < laserPosX; i++) {			  //check if there is any wall between the Robot and the laser
											if (game.getBoard().get(i, laserPosY).hasTile("Wall") && game.getBoard().get(i, laserPosY).getTile("Wall").getOrientations().get(0) == RIGHT){
												hasWall.add(true);
											}else if(game.getBoard().get(i, laserPosY).hasTile("Wall") && game.getBoard().get(i, laserPosY).getTile("Wall").getOrientations().get(0) == LEFT) {
												hasWall.add(true);
											}else{
												hasWall.add(false);
											}
										}

										if(game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == RIGHT){
											hasWall.add(true);
										}

										if(game.getBoard().get(laserPosX, laserPosY).hasTile("Wall") && game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().get(0) == LEFT){
											hasWall.add(true);
										}

										if (!(hasWall.contains(true))){
											isShot = true;
											newSortX.add(user.getRobot().getPosition().getX());

										}
									}
								}
							}

						if(newSortX.size() != 0){						//sort every Position that is relevant for the laser direction (x or y) and pick the lowest or the highest --> max or min value and coresponding user takes the spam
							Collections.sort(newSortX);
							int max = newSortX.get(newSortX.size()-1);
							for (User user : game.getPlayerQueue().getUsers()) {
								if (user.getRobot().getPosition().getX() == max && user.getRobot().getPosition().getY() == laserPosY) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									user.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), new String[]{"Spam"});
									server.broadcast(drawDamage);
								}
							}
						}
						if(isShot){
							break;
						}
					}

				}
				if (laserCell.getTile("Laser").getOrientations().contains(RIGHT)) {
					for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew <= 12; laserPosXNew++) {
						for (User user : game.getPlayerQueue().getUsers()) {
								if (laserPosY == user.getRobot().getPosition().getY() && laserPosXNew <= user.getRobot().getPosition().getX()) {
									//special case: laser on same tile as robot and opposite direction of the wall --> isShot
									if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT)
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosY) {


										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}

									//all other wall directions on other tiles --> isShot
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT) && user.getRobot().getPosition().getX() <=  laserPosXNew))) {

										isShot = true;
										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}

								//only Antenna- stop laser
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Antenna")) {
									break;
								}

									//only Robot shoot laser, stop laser
									if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall"))) {
										ArrayList<Boolean> hasWall = new ArrayList<>();

										for (int i = user.getRobot().getPosition().getX() - 1; i > laserPosX; i--) {
											if (game.getBoard().get(i, laserPosY).hasTile("Wall") && game.getBoard().get(i, laserPosY).getTile("Wall").getOrientations().get(0) == LEFT) {
												hasWall.add(true);
											}else if (game.getBoard().get(i, laserPosY).hasTile("Wall") && game.getBoard().get(i, laserPosY).getTile("Wall").getOrientations().get(0) == RIGHT) {
												hasWall.add(true);
											}else{
												hasWall.add(false);
											}
										}
										if(game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == LEFT){
											hasWall.add(true);
										}

										if(game.getBoard().get(laserPosX, laserPosY).hasTile("Wall") && game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().get(0) == RIGHT){
											hasWall.add(true);
										}
										if (!(hasWall.contains(true))) {
											isShot = true;
											newSortX.add(user.getRobot().getPosition().getX());
										}
									}
								}
							}


						if(newSortX.size() != 0){
							Collections.sort(newSortX);
							int min = newSortX.get(0);
							for (User user : game.getPlayerQueue().getUsers()) {
								if (user.getRobot().getPosition().getX() == min && user.getRobot().getPosition().getY() == laserPosY) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									user.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), new String[]{"Spam"});
									server.broadcast(drawDamage);
								}
							}
						}
						if(isShot){
							break;
						}
					}

				}
				if (laserCell.getTile("Laser").getOrientations().contains(TOP)) {
					for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew >= 0; laserPosYNew--) {
						for (User user : game.getPlayerQueue().getUsers()) {
							if (laserPosX == user.getRobot().getPosition().getX() && laserPosYNew >= user.getRobot().getPosition().getY()) {
								//special case: laser on same tile as robot and opposite direction of the wall --> isShot
								if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)
										&& user.getRobot().getPosition().getY() == laserPosY) {

									isShot = true;
									newSortY.add(user.getRobot().getPosition().getY());

									break;
								}

								//all other wall directions on other tiles --> isShot
								if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP) && user.getRobot().getPosition().getY() >= laserPosYNew))) {

									isShot = true;
									newSortY.add(user.getRobot().getPosition().getY());
									break;
								}

								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall"))) {
									ArrayList<Boolean> hasWall = new ArrayList<>();

									for (int i = user.getRobot().getPosition().getY() + 1; i < laserPosY; i++) {
										if (game.getBoard().get(laserPosX, i).hasTile("Wall") && game.getBoard().get(laserPosX, i).getTile("Wall").getOrientations().get(0) == BOTTOM) {
											hasWall.add(true);
										}else if(game.getBoard().get(laserPosX, i).hasTile("Wall") && game.getBoard().get(laserPosX, i).getTile("Wall").getOrientations().get(0) == TOP){
												hasWall.add(true);
										}else{
											hasWall.add(false);
										}
									}

									if(game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == BOTTOM){
										hasWall.add(true);
									}

									if(game.getBoard().get(laserPosX, laserPosY).hasTile("Wall") && game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().get(0) == TOP){
										hasWall.add(true);
									}
									if (!(hasWall.contains(true))){
										isShot = true;
										newSortY.add(user.getRobot().getPosition().getY());
									}

								}
							}

						}


						if (newSortY.size() != 0) {
							Collections.sort(newSortY);
							int max = newSortY.get(newSortY.size()-1);
							for (User user : game.getPlayerQueue().getUsers()) {
								if (user.getRobot().getPosition().getY() == max && user.getRobot().getPosition().getX() == laserPosX) {
									Spam spam = game.getSpamDeck().drawSpamCard();
									spam.setDiscarded(true);
									user.getPlayerInventory().getProgrammingDeck().addCard(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), new String[]{"Spam"});
									server.broadcast(drawDamage);
								}
							}
						}
						if(isShot){
							break;
						}
					}

				}


					if (laserCell.getTile("Laser").getOrientations().contains(BOTTOM)) {
						for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew <= 9; laserPosYNew++) {
							for (User user : game.getPlayerQueue().getUsers()) {
									if (laserPosY == user.getRobot().getPosition().getY() && laserPosYNew <= user.getRobot().getPosition().getY()) {
										//special case: laser on same tile as robot and opposite direction of the wall --> isShot
										if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP)
												&& user.getRobot().getPosition().getY() == laserPosY) {

											isShot = true;
											newSortY.add(user.getRobot().getPosition().getY());
											break;
										}

										//all other wall directions on other tiles --> isShot
										if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM) && user.getRobot().getPosition().getY() <= laserPosYNew))) {

											isShot = true;
											newSortY.add(user.getRobot().getPosition().getY());
											break;
										}

										//only Robot shoot laser, stop laser
										if ((!game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall"))) {
											ArrayList<Boolean> hasWall = new ArrayList<>();

											for (int i = user.getRobot().getPosition().getY() - 1; i > laserPosY; i--) {
												if (game.getBoard().get(laserPosX, i).hasTile("Wall") && game.getBoard().get(laserPosX, i).getTile("Wall").getOrientations().get(0) == TOP) {
													hasWall.add(true);
												} else if (game.getBoard().get(laserPosX, i).hasTile("Wall") && game.getBoard().get(laserPosX, i).getTile("Wall").getOrientations().get(0) == BOTTOM) {
													hasWall.add(true);
												} else {
													hasWall.add(false);
												}
											}
											if (game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).hasTile("Wall") && game.getBoard().get(user.getRobot().getPosition().getX(), user.getRobot().getPosition().getY()).getTile("Wall").getOrientations().get(0) == TOP) {
												hasWall.add(true);
											}

											if (game.getBoard().get(laserPosX, laserPosY).hasTile("Wall") && game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().get(0) == BOTTOM) {
												hasWall.add(true);
											}
											if (!(hasWall.contains(true))) {
												isShot = true;
												newSortY.add(user.getRobot().getPosition().getY());
											}

										}

									}
								}

							if(newSortY.size() != 0){
								Collections.sort(newSortY);
								int min = newSortY.get(0);
								for (User user : game.getPlayerQueue().getUsers()) {
									if (user.getRobot().getPosition().getY() == min && user.getRobot().getPosition().getX() == laserPosX) {
										Spam spam = game.getSpamDeck().drawSpamCard();
										spam.setDiscarded(true);
										user.getPlayerInventory().getProgrammingDeck().addCard(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), new String[]{"Spam"});
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
	}

