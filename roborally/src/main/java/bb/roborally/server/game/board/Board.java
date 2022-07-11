package bb.roborally.server.game.board;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.tiles.ConveyorBelt;
import bb.roborally.server.game.tiles.Laser;
import bb.roborally.server.game.tiles.StartPoint;
import bb.roborally.server.game.tiles.Tile;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class Board implements Message {
	private ArrayList<ArrayList<Cell>> gameMap;

	public Board(ArrayList<ArrayList<Cell>> gameMap) {
		this.gameMap = gameMap;
	}

	public ArrayList<ArrayList<Cell>> getGameMap() {
		return gameMap;
	}

	public void setGameMap(ArrayList<ArrayList<Cell>> gameMap) {
		this.gameMap = gameMap;
	}

	public ArrayList<Cell> getStartPoints() {
		ArrayList<Cell> startPoints = new ArrayList<>();
		for (ArrayList<Cell> cellsRow: this.gameMap) {
			for (Cell cell: cellsRow) {
				for (Tile tile: cell.getTiles()) {
					if (tile instanceof StartPoint) {
						startPoints.add(cell);
					}
				}
			}
		}
		return startPoints;
	}

	public ArrayList<Cell> getBlueConveyorBelts() {
		ArrayList<Cell> blueConveyorBelts = new ArrayList<>();
		for (ArrayList<Cell> cellsRow: this.gameMap) {
			for (Cell cell: cellsRow) {
				for (Tile tile: cell.getTiles()) {
					if (tile instanceof ConveyorBelt) {
						ConveyorBelt conveyorBelt = (ConveyorBelt) tile;
						if (conveyorBelt.getSpeed() == 2) {
							blueConveyorBelts.add(cell);
						}
					}
				}
			}
		}
		return blueConveyorBelts;
	}

	public ArrayList<Cell> getBoardLaser(){
		ArrayList<Cell> boardLaser =new ArrayList<>();
		for (ArrayList<Cell> cellsRow: this.gameMap){
			for (Cell cell: cellsRow){
				for (Tile tile: cell.getTiles()){
					if (tile instanceof Laser){
						boardLaser.add(cell);
					}
				}
			}
		}
		return boardLaser;
	}

	public Cell get(int x, int y) {
		return gameMap.get(x).get(y);
	}

	@Override
	public String toJson() {
		return toEnvelope().toJson();
	}

	@Override
	public Envelope toEnvelope() {
		return new Envelope(Envelope.MessageType.GAME_STARTED, this);
	}

}



