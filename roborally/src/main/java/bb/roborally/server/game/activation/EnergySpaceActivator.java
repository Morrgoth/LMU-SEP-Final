package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.Energy;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;
import bb.roborally.protocol.map.tiles.EnergySpace;
import bb.roborally.protocol.map.tiles.Tile;

import java.util.ArrayList;

/**
 * @author Philipp Keyzman
 */
public class EnergySpaceActivator {
	private Server server;
	private Game game;

	public EnergySpaceActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}
	public void activate() {
		Animation animation = new Animation("EnergySpace");
		server.broadcast(animation);
		ArrayList<ServerCell> energySpaceList = game.getBoard().getEnergySpace();
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
				for(ServerCell energySpaceServerCell : energySpaceList){
					for(Tile tile : energySpaceServerCell.getTiles()){
						if(tile instanceof EnergySpace) {
							if(((EnergySpace) tile).getCount()>= 1){
								user.getPlayerInventory().increaseEnergyCubeAmountBy(1);
								((EnergySpace) tile).decreaseRemainedEnergyCube();

								server.broadcast(new Energy(user.getClientID(), user.getPlayerInventory().getEnergy(),"EnergySpace"));
							}
						}
					}
				}
			}
		}
	}
}
