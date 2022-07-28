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

		boolean isShooted;
		ArrayList<ServerCell> laserList = game.getBoard().getBoardLaser();
		for (ServerCell laserCell : laserList) {
			newSortX.clear();
			newSortY.clear();
			int number = ((Laser) laserCell.getTile("Laser")).getCount();
			int register = ActivationPhaseHandler.getRegister();

			if (number == register) {

				int laserPosX = laserCell.getPosition().getX();
				int laserPosY = laserCell.getPosition().getY();

				isShooted = false;

				if (laserCell.getTile("Laser").getOrientations().contains(LEFT)) {
					for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew >= 0; laserPosXNew--) {
						for (User user : game.getPlayerQueue().getUsers()) {
							//for (int i = newSortX.size() - 1; i >= 0; i--) {
								if (laserPosY == user.getRobot().getPosition().getY() && laserPosXNew >= user.getRobot().getPosition().getX()) {

									//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
									if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosY) {


										isShooted = true;
										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}


									//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT) && user.getRobot().getPosition().getX() >= laserPosXNew))) {

										isShooted = true;
										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}

									//only Robot shoot laser, stop laser
									if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall"))) {
										ArrayList<Boolean> hasWall = new ArrayList<>();


										for (int i = user.getRobot().getPosition().getX() + 1; i < laserPosX; i++) {
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
											isShooted = true;
											newSortX.add(user.getRobot().getPosition().getX());

										}

									}
								}
						}

						if(newSortX.size() != 0){
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
						if(isShooted){
							break;
						}
					}

				}
				if (laserCell.getTile("Laser").getOrientations().contains(RIGHT)) {
					for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew <= 12; laserPosXNew++) {
						for (User user : game.getPlayerQueue().getUsers()) {
							//for (int i = 0; i >= newSortX.size() - 1; i++) {
								if (laserPosY == user.getRobot().getPosition().getY() && laserPosXNew <= user.getRobot().getPosition().getX()) {
									//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
									if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT)
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosY) {


										newSortX.add(user.getRobot().getPosition().getX());
										break;
									}

									//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT) && user.getRobot().getPosition().getX() <=  laserPosXNew))) {

										isShooted = true;
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
											isShooted = true;
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
						if(isShooted){
							break;
						}
					}

				}
				if (laserCell.getTile("Laser").getOrientations().contains(TOP)) {
					for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew >= 0; laserPosYNew--) {
						for (User user : game.getPlayerQueue().getUsers()) {
							//for (int i = newSortY.size() - 1; i >= 0; i--) {
							if (laserPosX == user.getRobot().getPosition().getX() && laserPosYNew >= user.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)
										&& user.getRobot().getPosition().getY() == laserPosY) {

									isShooted = true;
									newSortY.add(user.getRobot().getPosition().getY());

									break;
								}

								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP) && user.getRobot().getPosition().getY() >= laserPosYNew))) {

									isShooted = true;
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
										isShooted = true;
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
						if(isShooted){
							break;
						}
					}

				}


					if (laserCell.getTile("Laser").getOrientations().contains(BOTTOM)) {
						for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew <= 9; laserPosYNew++) {
							for (User user : game.getPlayerQueue().getUsers()) {
								//Bottom
								//for (int i = 0; i >= newSortY.size() - 1; i++) {
									if (laserPosY == user.getRobot().getPosition().getY() && laserPosYNew <= user.getRobot().getPosition().getY()) {
										//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
										if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP)
												&& user.getRobot().getPosition().getY() == laserPosY) {

											isShooted = true;
											newSortY.add(user.getRobot().getPosition().getY());
											break;
										}

										//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
										if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM) && user.getRobot().getPosition().getY() <= laserPosYNew))) {

											isShooted = true;
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
												isShooted = true;
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
								if(isShooted){
									break;
								}
							}
						}
					}
				}
			}
		}
	}

