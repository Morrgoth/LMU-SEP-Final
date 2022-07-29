package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.protocol.game_events.CheckPointReached;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.ServerCell;
import bb.roborally.protocol.map.tiles.CheckPoint;
import bb.roborally.protocol.map.tiles.Tile;

import java.util.ArrayList;

/**
 * @author Philipp Keyzman
 */
public class CheckPointActivator {

	private Server server;
	private Game game;

	public CheckPointActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}

	public void activate() {
		Animation animation = new Animation("CheckPoint");
		server.broadcast(animation);

		ArrayList<ServerCell> checkPointList = game.getBoard().getCheckPoint();
		for (User user : game.getPlayerQueue().getUsers()) {
			boolean isOnTile = false;
			int counter = 0;

			while (counter < checkPointList.size() && !isOnTile) {
				if (checkPointList.get(counter).getPosition().equals(user.getRobot().getPosition())) {
					isOnTile = true;
				}
				counter += 1;
			}
			if (isOnTile) {
				for (ServerCell checkPointServerCell : checkPointList) {
					for (Tile tile : checkPointServerCell.getTiles()) {
						if (tile instanceof CheckPoint) {
							if (user.getPlayerInventory().getCheckPointTokens() == ((CheckPoint) tile).getCount() -1) {
								user.getPlayerInventory().addCheckPointTokens();
								server.broadcast(new CheckPointReached(user.getClientID(), user.getPlayerInventory().getCheckPointTokens()));
							}
						}
					}
				}
			}
		}
	}
}