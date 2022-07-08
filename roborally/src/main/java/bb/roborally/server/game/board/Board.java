package bb.roborally.server.game.board;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.tiles.ConveyorBelt;
import bb.roborally.server.game.tiles.Gear;
import bb.roborally.server.game.tiles.StartPoint;
import bb.roborally.server.game.tiles.Tile;

import java.lang.reflect.Array;
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

	public ArrayList<Cell> getClockwiseGears(){
		ArrayList<Cell> clockwiseGears = new ArrayList<>();
		for(ArrayList<Cell> cellsRow: this.gameMap){
			for(Cell cell: cellsRow){
				for(Tile tile: cell.getTiles()){
					if(tile instanceof Gear){
						Gear gear = (Gear) tile;
						if(gear.getOrientations().get(0) == Orientation.CLOCKWISE){
							clockwiseGears.add(cell);
						}
					}
				}
			}
		}
		return clockwiseGears;
	}

	public ArrayList<Cell> getCounterclockwiseGears(){
		ArrayList<Cell> counterclockwiseGears = new ArrayList<>();
		for(ArrayList<Cell> cellsRow: this.gameMap){
			for(Cell cell: cellsRow){
				for(Tile tile: cell.getTiles()){
					if(tile instanceof Gear){
						Gear gear = (Gear) tile;
						if(gear.getOrientations().get(0) == Orientation.COUNTERCLOCKWISE){
							counterclockwiseGears.add(cell);
						}
					}
				}
			}
		}
		return counterclockwiseGears;
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



