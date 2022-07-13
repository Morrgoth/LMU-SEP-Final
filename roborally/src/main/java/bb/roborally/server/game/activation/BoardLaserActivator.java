package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.ClientList;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.server.game.tiles.Antenna;
import bb.roborally.server.game.tiles.Laser;
import bb.roborally.server.game.tiles.Tile;
import bb.roborally.server.game.tiles.Wall;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.*;

public class BoardLaserActivator {
	private Server server;
	private Game game;

	public BoardLaserActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}

	public void activate() {
		Animation animation = new Animation("Laser");
		try {
			server.broadcast(animation);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ArrayList<Cell> laserList = game.getBoard().getBoardLaser();
		for (Cell laserCell : laserList) {
			for (Tile tile : laserCell.getTiles()) {
				if (tile instanceof Laser) {
					if (((Laser) tile).getCount() == ActivationPhaseHandler.getRegister()) {

						boolean shootingEnd = false;
						int counter = 0;
						int counterWall = game.getBoard().getWall().size();
						int counterRobot = game.getPlayerQueue().getUsers().size();

						int wallPositionX = game.getBoard().getWall().get(counterWall).getPosition().getX();
						int wallPositionY = game.getBoard().getWall().get(counterWall).getPosition().getY();

						ArrayList<Orientation> wallOrientation = game.getBoard().getWall().get(counterWall).getTile("Wall").getOrientations();

						int antennaPositionX = game.getBoard().getAntenna().get(0).getPosition().getX();
						int antennaPositionY = game.getBoard().getAntenna().get(0).getPosition().getY();

						int robotPositionX = game.getPlayerQueue().getUsers().get(counterRobot).getRobot().getPosition().getX();
						int robotPositionY = game.getPlayerQueue().getUsers().get(counterRobot).getRobot().getPosition().getY();
						int laserPosX = laserCell.getPosition().getX();
						int laserPosY = laserCell.getPosition().getY();
						int caseType = 0;

						Spam spam = new Spam();

						while (counter <= laserList.size()) {
							if (tile.getOrientations().equals(LEFT)) {
								for (laserPosX = laserCell.getPosition().getX(); laserPosX >= 0; laserPosX--) {
									if (laserPosY == robotPositionY) {
										while (!shootingEnd) {

											if (laserPosX == robotPositionX) {
												caseType = 1;
											}
											if (antennaPositionX > robotPositionX) {
												caseType = 2;
											}
											if (wallPositionX > robotPositionX) {
												caseType = 3;
											}
											if (wallPositionX == robotPositionX && (wallOrientation.equals(tile.getOrientations()))) {
												caseType = 4;
											}
										}
										switch (caseType) {
											case 1://shoot
											case 4:
												//Spam spam = new Spam();
												game.getPlayerQueue().getUsers().get(counter).getProgrammingDeck().addCard(spam, true);
												break;
											case 2://!shoot turn ends
												break;
											case 3://!shoot turn ends
												break;
											default:
										}
										shootingEnd = true;
									}
								}
							}
							if (tile.getOrientations().equals(RIGHT)) {
								for (laserPosX = laserCell.getPosition().getX(); laserPosX <= game.getBoard().getGameMap().size(); laserPosX++) {
									if (laserPosY == robotPositionY) {
										while (!shootingEnd) {

											if (laserPosX == robotPositionX) {
												caseType = 1;
											}
											if (antennaPositionX < robotPositionX) {
												caseType = 2;
											}
											if (wallPositionX < robotPositionX) {
												caseType = 3;
											}
											if (wallPositionX == robotPositionX && (wallOrientation.equals(tile.getOrientations()))) {
												caseType = 4;
											}
										}
										switch (caseType) {
											case 1://shoot
											case 4:
												//Spam spam = new Spam();
												game.getPlayerQueue().getUsers().get(counter).getProgrammingDeck().addCard(spam, true);
												break;
											case 2://!shoot turn ends
												break;
											case 3://!shoot turn ends
												break;
											default:
										}
										shootingEnd = true;
									}
								}
							}

							if (tile.getOrientations().equals(TOP)) {
								for (laserPosY = laserCell.getPosition().getY(); laserPosY <= 0; laserPosY--) {
									if (laserPosX == robotPositionX) {
										while (!shootingEnd) {

											if (laserPosY == robotPositionY) {
												caseType = 1;
											}
											if (antennaPositionY > robotPositionY) {
												caseType = 2;
											}
											if (wallPositionY > robotPositionY) {
												caseType = 3;
											}
											if (wallPositionY == robotPositionY && (wallOrientation.equals(tile.getOrientations()))) {
												caseType = 4;
											}
										}
										switch (caseType) {
											case 1://shoot
											case 4:
												//Spam spam = new Spam();
												game.getPlayerQueue().getUsers().get(counter).getProgrammingDeck().addCard(spam, true);
												break;
											case 2://!shoot turn ends
												break;
											case 3://!shoot turn ends
												break;
											default:
										}
										shootingEnd = true;
									}
								}
							}

							if (tile.getOrientations().equals(BOTTOM)) {
								for (laserPosY = laserCell.getPosition().getY(); laserPosY <= game.getBoard().getGameMap().size(); laserPosY++) {
									if (laserPosX == robotPositionX) {
										while (!shootingEnd) {

											if (laserPosY == robotPositionY) {
												caseType = 1;
											}
											if (antennaPositionY < robotPositionY) {
												caseType = 2;
											}
											if (wallPositionY < robotPositionY) {
												caseType = 3;
											}
											if (wallPositionY == robotPositionY && (wallOrientation.equals(tile.getOrientations()))) {
												caseType = 4;
											}
										}
										switch (caseType) {
											case 1://shoot
											case 4:
												//Spam spam = new Spam();
												game.getPlayerQueue().getUsers().get(counter).getProgrammingDeck().addCard(spam, true);
												break;
											case 2://!shoot turn ends
												break;
											case 3://!shoot turn ends
												break;
											default:
										}
										shootingEnd = true;
									}
								}
							}
							counter += 1;
						}
					}
				}
			}
		}
	}
}
							/*
	+1.getLaserPosition with !!correct register!! from activationPhaseHandler
	2.
	3. get  Orientation
	4. if RIGHT iterate over x += 1; coordinate until robot or wall or antenna isOnTile,
	 	if robot and wall and wall Orientation != RIGHT shoot Robot
		else break
	6. repeat for each Orientation
	 */

