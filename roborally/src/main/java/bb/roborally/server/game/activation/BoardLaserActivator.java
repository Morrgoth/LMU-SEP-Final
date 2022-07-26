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

	public BoardLaserActivator(Server server, Game game,int register) {
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
					for (User user : game.getPlayerQueue().getUsers()) {
						for (int counter = 0; laserPosY - counter >= 0; counter++) {
							for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew >= 0; laserPosXNew--) {
								if (laserCell.getPosition().getY() - counter == user.getRobot().getPosition().getY()) {
									if (game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserCell.getPosition().getX()
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}

									//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserCell.getPosition().getX()
											&& user.getRobot().getPosition().getY() == laserCell.getPosition().getY()
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT))) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
									//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosXNew
											&& user.getRobot().getPosition().getY() == laserPosY
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(TOP) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(BOTTOM)))) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
									//wenn entgegegesetzt kein Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosXNew
											&& user.getRobot().getPosition().getY() == laserPosY
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)))) {

										break;
									}
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
									//only Robot shoot laser, stop laser
									if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosXNew
											&& user.getRobot().getPosition().getY() == laserPosY)) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
								}
							}
						}
					}
				}
				if (laserCell.getTile("Laser").getOrientations().contains(RIGHT)) {
					for (User user : game.getPlayerQueue().getUsers()) {
						for (int counter = 0; laserPosX + counter <= 12; counter++) {
							for (int laserPosXNew = laserCell.getPosition().getX(); laserPosXNew <= 12; laserPosXNew++) {
								if (laserCell.getPosition().getX() + counter == user.getRobot().getPosition().getX()) {
									if (game.getBoard().get(laserPosX, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserCell.getPosition().getX()
											&& game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().contains(RIGHT)) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}

									//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserCell.getPosition().getX()
											&& user.getRobot().getPosition().getY() == laserCell.getPosition().getY()
											&& game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT))) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
									//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosXNew
											&& user.getRobot().getPosition().getY() == laserPosY
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(RIGHT) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(TOP) || game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(BOTTOM)))) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
									//wenn entgegegesetzt kein Abschuss
									if ((game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosXNew
											&& user.getRobot().getPosition().getY() == laserPosY
											&& (game.getBoard().get(laserPosXNew, laserPosY).getTile("Wall").getOrientations().contains(LEFT)))) {

										break;
									}
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
									//only Robot shoot laser, stop laser
									if ((!game.getBoard().get(laserPosXNew, laserPosY).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosXNew
											&& user.getRobot().getPosition().getY() == laserPosY)) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
								}
							}
						}
					}
				}
				if (laserCell.getTile("Laser").getOrientations().contains(TOP)) {
					for (User user : game.getPlayerQueue().getUsers()) {
						for (int counter = 0; laserPosY - counter >= 0; counter++) {
							for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew >= 0; laserPosYNew--) {
								if (laserCell.getPosition().getY() - counter == user.getRobot().getPosition().getY()) {

									//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
									if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserCell.getPosition().getX()
											&& user.getRobot().getPosition().getY() == laserCell.getPosition().getY()
											&& game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().contains(BOTTOM))) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
									//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
									if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosYNew
											&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP)))) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
									//wenn entgegegesetzt kein Abschuss
									if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosYNew
											&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)))) {

										break;
									}
									//only Wall - stop laser
									if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
											&& user.getRobot().getPosition().getX() != laserPosX
											|| user.getRobot().getPosition().getY() != laserPosYNew)) {

										break;
									}
									//only Antenna- stop laser
									if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Antenna")) {
										break;
									}
									//only Robot shoot laser, stop laser
									if ((!game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
											&& user.getRobot().getPosition().getX() == laserPosX
											&& user.getRobot().getPosition().getY() == laserPosYNew)) {

										user.getProgrammingDeck().getDiscardPile().add(spam);
										DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
										server.broadcast(drawDamage);
										break;
									}
								}
							}
						}
					}
					if (laserCell.getTile("Laser").getOrientations().contains(BOTTOM)) {
						for (User user : game.getPlayerQueue().getUsers()) {
							for (int counter = 0; laserPosY - counter >= 9; counter++) {
								for (int laserPosYNew = laserCell.getPosition().getY(); laserPosYNew <= 9; laserPosYNew++) {
									if (laserCell.getPosition().getY() + counter == user.getRobot().getPosition().getY()) {


										//Spezialfall laser auf dem gleichen Tile mit Robot bei entgegesetzter Wall - Abschuss
										if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& user.getRobot().getPosition().getX() == laserCell.getPosition().getX()
												&& user.getRobot().getPosition().getY() == laserCell.getPosition().getY()
												&& game.getBoard().get(laserPosX, laserPosY).getTile("Wall").getOrientations().contains(TOP))) {

											user.getProgrammingDeck().getDiscardPile().add(spam);
											DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
											server.broadcast(drawDamage);
											break;
										}
										//sonst alle Orientations ausser entgegegesetzt liegende führen zum Abschuss
										if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& user.getRobot().getPosition().getX() == laserPosX
												&& user.getRobot().getPosition().getY() == laserPosYNew
												&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(LEFT) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(RIGHT) || game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(BOTTOM)))) {

											user.getProgrammingDeck().getDiscardPile().add(spam);
											DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
											server.broadcast(drawDamage);
											break;
										}
										//wenn entgegegesetzt kein Abschuss
										if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& user.getRobot().getPosition().getX() == laserPosX
												&& user.getRobot().getPosition().getY() == laserPosYNew
												&& (game.getBoard().get(laserPosX, laserPosYNew).getTile("Wall").getOrientations().contains(TOP)))) {

											break;
										}
										//only Wall - stop laser
										if ((game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& user.getRobot().getPosition().getX() != laserPosX
												|| user.getRobot().getPosition().getY() != laserPosYNew)) {

											break;
										}
										//only Antenna- stop laser
										if (game.getBoard().get(laserPosX, laserPosYNew).hasTile("Antenna")) {
											break;
										}
										//only Robot shoot laser, stop laser
										if ((!game.getBoard().get(laserPosX, laserPosYNew).hasTile("Wall")
												&& user.getRobot().getPosition().getX() == laserPosX
												&& user.getRobot().getPosition().getY() == laserPosYNew)) {

											user.getProgrammingDeck().getDiscardPile().add(spam);
											DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Spam");
											server.broadcast(drawDamage);
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
	}
}

