package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.CheckPointReached;
import bb.roborally.protocol.game_events.Energy;
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
		ArrayList<Cell> energySpaceList = game.getBoard().getEnergySpace();
		for (User user: game.getPlayerQueue().getUsers()) {
			boolean isOnTile = false;
			int counter = 0;

			while (counter < energySpaceList.size() && !isOnTile) {
				if (energySpaceList.get(counter).getPosition().equals(user.getRobot().getPosition())) {
					isOnTile = true;
				}
				counter += 1;
			}
			if(isOnTile){
				for(Cell energySpaceCell : energySpaceList){
					for(Tile tile : energySpaceCell.getTiles()){
						if(tile instanceof EnergySpace) {
							if(((EnergySpace) tile).getCount()>= 1){
								user.getPlayerInventory().increaseEnergyCubeAmountBy(1);
								((EnergySpace) tile).decreaseRemainedEnergyCube();

								try {
									server.broadcast(new Energy(user.getClientID(), user.getRobot().getEnergyCubeAmount(),"EnergySpace"));
								} catch (IOException e) {
									throw new RuntimeException(e);
								}
							}
						}
					}
				}
			}
		}
	}
}
