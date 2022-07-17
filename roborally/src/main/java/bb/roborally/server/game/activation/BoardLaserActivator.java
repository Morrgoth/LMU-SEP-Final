package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.server.game.tiles.Laser;
import bb.roborally.server.game.tiles.Tile;
import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.*;

public class BoardLaserActivator {
	private Server server;
	private Game game;
	public boolean isShootingEnded = false;

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

						int laserPosX = laserCell.getPosition().getX();
						int laserPosY = laserCell.getPosition().getY();

						int laserCase = 0;

						Spam spam = new Spam();

						while(!isShootingEnded){

							if(tile.getOrientations().equals(LEFT)){
								for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew >= 0; laserPosXNew--) {

									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(LEFT)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(RIGHT)
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY)) {

										isShootingEnded = true;
									}
									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(TOP)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(BOTTOM)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if(game.getBoard().get(laserPosXNew,laserPosY).hasTile("Antenna")) {
										isShootingEnded = true;
									}
									if( (!game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))){

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 !game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))){

										isShootingEnded = true;
									}
								}
							}

							if(tile.getOrientations().equals(RIGHT)){
								for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew <= game.getBoard().getGameMap().size(); laserPosXNew++) {

									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(LEFT)) {

										isShootingEnded = true;
									}
									if( game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(RIGHT)
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(TOP)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))
											&&	 game.getBoard().get(laserPosXNew,laserPosY).getTile("Wall").getOrientations().equals(BOTTOM)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if(game.getBoard().get(laserPosXNew,laserPosY).hasTile("Antenna")) {
										isShootingEnded = true;
									}
									if( (!game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))){

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosXNew,laserPosY).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosXNew,laserPosY).hasTile("Wall")
											&&	 !game.getRobotList().isRobotOnPosition(laserPosXNew,laserPosY))){

										isShootingEnded = true;
									}
								}
							}

							if(tile.getOrientations().equals(TOP)){
								for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew >= 0; laserPosYNew--) {

									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(LEFT)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(RIGHT)
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(TOP)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(BOTTOM)) {

										isShootingEnded = true;
									}
									if(game.getBoard().get(laserPosX,laserPosYNew).hasTile("Antenna")) {
										isShootingEnded = true;
									}
									if( (!game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))){

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 !game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))){

										isShootingEnded = true;
									}
								}
							}

							if(tile.getOrientations().equals(BOTTOM)){
								for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew <= game.getBoard().getGameMap().size(); laserPosYNew++) {

									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(LEFT)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(RIGHT)
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(TOP)) {

										isShootingEnded = true;
									}
									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))
											&&	 game.getBoard().get(laserPosX,laserPosYNew).getTile("Wall").getOrientations().equals(BOTTOM)) {

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if(game.getBoard().get(laserPosX,laserPosYNew).hasTile("Antenna")) {
										isShootingEnded = true;
									}
									if( (!game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))){

										int playerID = game.getRobotList().getRobotIDByPosition(laserPosX,laserPosYNew).getFigureId();

										game.getPlayerQueue().getUsers().get(playerID).getProgrammingDeck().addCard(spam, true);
										isShootingEnded = true;
										try {
											server.broadcast(new DrawDamage(playerID, "Spam"));
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									}
									if( (game.getBoard().get(laserPosX,laserPosYNew).hasTile("Wall")
											&&	 !game.getRobotList().isRobotOnPosition(laserPosX,laserPosYNew))){

										isShootingEnded = true;
									}
								}
							}
						}
					}
				}
			}
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
