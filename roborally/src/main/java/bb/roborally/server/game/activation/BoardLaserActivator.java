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

import static bb.roborally.server.game.Orientation.*;

public class BoardLaserActivator {
	private Server server;
	private Game game;

	//f[r test
	int register;

	public BoardLaserActivator(Server server, Game game, int register) {
		this.server = server;
		this.game = game;
		this.register = register;
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
					for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew >= 0; laserPosXNew--) {
						for (User user : game.getPlayerQueue().getUsers()) {
							if (laserPosXNew  == user.getRobot().getPosition().getX()
									&& laserPosY  == user.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)
										&& user.getRobot().getPosition().getX() == laserPosX
										&& user.getRobot().getPosition().getY() == laserPosY)) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								//wenn entgegegesetzt kein Abschuss
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)) {

									break;
								}
								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(TOP) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(BOTTOM)))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								/*
								//only Wall - stop laser
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& user.getRobot().getPosition().getX() != laserPosXNew
										|| user.getRobot().getPosition().getY() != laserPosY)) {

									break;
								}
								//only Antenna- stop laser
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Antenna")) {
									break;
								}

								 */
								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall"))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								else {
									break;
								}
							}
						}
					}
				}
				if (laserCell.getTile("Laser").getOrientations().contains(RIGHT)) {
					for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew <= 12; laserPosXNew++) {
						for (User user : game.getPlayerQueue().getUsers()) {
							if (laserPosXNew  == user.getRobot().getPosition().getX()
									&& laserPosY  == user.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)
										&& user.getRobot().getPosition().getX() == laserPosX
										&& user.getRobot().getPosition().getY() == laserPosY)) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								//wenn entgegegesetzt kein Abschuss
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)) {

									break;
								}
								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(TOP) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(BOTTOM)))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								/*
								//only Wall - stop laser
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& user.getRobot().getPosition().getX() != laserPosXNew
										|| user.getRobot().getPosition().getY() != laserPosY)) {

									break;
								}
								//only Antenna- stop laser
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Antenna")) {
									break;
								}

								 */
								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall"))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								else {
									break;
								}
							}
						}
					}
				}
				if (laserCell.getTile("Laser").getOrientations().contains(TOP)) {
					for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew >= 0; laserPosYNew--) {
						for (User user : game.getPlayerQueue().getUsers()) {
							if (laserPosX  == user.getRobot().getPosition().getX()
									&& laserPosYNew  == user.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT)
										&& user.getRobot().getPosition().getX() == laserPosX
										&& user.getRobot().getPosition().getY() == laserPosY)) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								//wenn entgegegesetzt kein Abschuss
								if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT)) {

									break;
								}
								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								/*
								//only Wall - stop laser
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& user.getRobot().getPosition().getX() != laserPosXNew
										|| user.getRobot().getPosition().getY() != laserPosY)) {

									break;
								}
								//only Antenna- stop laser
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Antenna")) {
									break;
								}

								 */
								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall"))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								else {
									break;
								}
							}
						}
					}
				}
				if (laserCell.getTile("Laser").getOrientations().contains(BOTTOM)) {
					for (int laserPosYNew = laserCell.getPosition().getX(); laserPosYNew <= 9; laserPosYNew++) {
						for (User user : game.getPlayerQueue().getUsers()) {
							if (laserPosX  == user.getRobot().getPosition().getX()
									&& laserPosYNew  == user.getRobot().getPosition().getY()) {
								//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
								if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT)
										&& user.getRobot().getPosition().getX() == laserPosX
										&& user.getRobot().getPosition().getY() == laserPosY)) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								//wenn entgegegesetzt kein Abschuss
								if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT)) {

									break;
								}
								//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
								if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
										&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								/*
								//only Wall - stop laser
								if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
										&& user.getRobot().getPosition().getX() != laserPosXNew
										|| user.getRobot().getPosition().getY() != laserPosY)) {

									break;
								}
								//only Antenna- stop laser
								if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Antenna")) {
									break;
								}

								 */
								//only Robot shoot laser, stop laser
								if ((!game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall"))) {

									user.getProgrammingDeck().getDiscardPile().add(spam);
									DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
									server.broadcast(drawDamage);
									break;
								}
								else {
									break;
								}
							}
						}
					}
				}
			}
		}
	}
}
