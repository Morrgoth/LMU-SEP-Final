package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.CheckPoint;
import bb.roborally.server.game.tiles.Tile;

import java.io.IOException;
import java.util.ArrayList;

public class CheckPointActivator {

	private Server server;
	private Game game;

	public CheckPointActivator(Server server, Game game) {
		this.server = server;
		this.game = game;
	}

	public void activate() {
		Animation animation = new Animation("CheckPoint");
		try {
			server.broadcast(animation);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ArrayList<Cell> checkPointList = game.getBoard().getCheckPoint();
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
				for (Cell checkPointCell : checkPointList) {
					for (Tile tile : checkPointCell.getTiles()) {
						if (tile instanceof CheckPoint) {
							if (user.getRobot().getCheckPointTokens() < ((CheckPoint) tile).getCount()) {
								user.getRobot().addCheckPointTokens();
							}
						}
					}
				}
			}
		}
	}
}