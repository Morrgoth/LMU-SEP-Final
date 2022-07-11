package bb.roborally.server.game.board;

import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.Message;
import bb.roborally.server.game.activation.ActivationPhaseHandler;
import bb.roborally.server.game.tiles.*;

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

	public ArrayList<Cell> getEnergySpace(){
		ArrayList<Cell> energySpace =new ArrayList<>();
		for (ArrayList<Cell> cellsRow: this.gameMap){
			for (Cell cell: cellsRow){
				for (Tile tile: cell.getTiles()){
					if (tile instanceof EnergySpace){
						energySpace.add(cell);
					}
				}
			}
		}
		return energySpace;
	}

	public ArrayList<Cell> getCheckPoint(){
		ArrayList<Cell> checkPoint =new ArrayList<>();
		for (ArrayList<Cell> cellsRow: this.gameMap){
			for (Cell cell: cellsRow){
				for (Tile tile: cell.getTiles()){
					if (tile instanceof CheckPoint){
						checkPoint.add(cell);
					}
				}
			}
		}
		return checkPoint;
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



