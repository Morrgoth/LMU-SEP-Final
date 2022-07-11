package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.EnergySpace;
import bb.roborally.server.game.tiles.Laser;
import bb.roborally.server.game.tiles.Tile;

import java.io.IOException;
import java.util.ArrayList;

public class EnergySpaceActivator {
	private Server server;
	private Game game;

	public EnergySpaceActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}
	public void activate() {
		Animation animation = new Animation("EnergySpace");
		try {
			server.broadcast(animation);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		ArrayList<Cell> energyList = game.getBoard().getBoardLaser();
		for (User user: game.getPlayerQueue().getUsers()) {
			boolean isOnTile = false;
			int counter = 0;

			while (counter < energyList.size() && !isOnTile) {
				if (energyList.get(counter).getPosition().equals(user.getRobot().getPosition())) {
					isOnTile = true;
				}
				counter += 1;
			}
			if(isOnTile){
				user.getRobot().increaseEnergyCubeAmountBy(1);
				//energySpace decreaseRemainedEnergyCube();
				energyList
			}
		}

		for (Cell energyCell : energyList) {
			for (Tile tile : energyCell.getTiles()) {
				if (tile instanceof EnergySpace) {

				}
			}
		}
	}
}
