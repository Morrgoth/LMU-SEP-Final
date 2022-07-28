package bb.roborally.server.game.board;

import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.tiles.*;
import bb.roborally.protocol.Orientation;

import java.util.ArrayList;

/**
 * author Philipp Keyzman
 */
public class ServerBoard {

	private final Board board;
	private final ArrayList<ArrayList<ServerCell>> map = new ArrayList<>();
	public ServerBoard(Board board) {
		this.board = board;
		setCells(board.getCells());
	}

	public ArrayList<ArrayList<ServerCell>> getMap() {
		return map;
	}

	public ServerCell getAntenna(){
		for(ArrayList<ServerCell> cellsRow: this.map){
			for(ServerCell serverCell : cellsRow){
				for(Tile tile: serverCell.getTiles()){
					if(tile instanceof Antenna){
						return serverCell;
					}
				}
			}
		}
		return null;
	}

	public ArrayList<ServerCell> getStartPoints() {
		ArrayList<ServerCell> startPoints = new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map) {
			for (ServerCell serverCell : cellsRow) {
				for (Tile tile: serverCell.getTiles()) {
					if (tile instanceof StartPoint) {
						startPoints.add(serverCell);
					}
				}
			}
		}
		return startPoints;
	}

	public ArrayList<ServerCell> getBlueConveyorBelts() {
		ArrayList<ServerCell> blueConveyorBelts = new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map) {
			for (ServerCell serverCell : cellsRow) {
				for (Tile tile: serverCell.getTiles()) {
					if (tile instanceof ConveyorBelt) {
						ConveyorBelt conveyorBelt = (ConveyorBelt) tile;
						if (conveyorBelt.getSpeed() == 2) {
							blueConveyorBelts.add(serverCell);
						}
					}
				}
			}
		}
		return blueConveyorBelts;
	}

	public ArrayList<ServerCell> getGreenConveyorBelts(){
		ArrayList<ServerCell> greenConveyorBelts = new ArrayList<>();
		for(ArrayList<ServerCell> cellsRow: this.map){
			for(ServerCell serverCell : cellsRow){
				for(Tile tile: serverCell.getTiles()){
					if(tile instanceof ConveyorBelt){
						ConveyorBelt conveyorBelt = (ConveyorBelt) tile;
						if(conveyorBelt.getSpeed() == 1){
							greenConveyorBelts.add(serverCell);
						}
					}
				}
			}
		}
		return greenConveyorBelts;
	}

	public ArrayList<ServerCell> getPushPanels(int register){
		ArrayList<ServerCell> pushPanels = new ArrayList<>();
		for(ArrayList<ServerCell> cellsRow: this.map) {
			for (ServerCell serverCell : cellsRow) {
				for (Tile tile : serverCell.getTiles()) {
					if (tile instanceof PushPanel) {
						if(((PushPanel) tile).getRegisters().contains(register)){
							pushPanels.add(serverCell);
						}
					}
				}
			}
		}
		return pushPanels;
	}

	public ArrayList<ServerCell> getClockwiseGears(){
		ArrayList<ServerCell> clockwiseGears = new ArrayList<>();
		for(ArrayList<ServerCell> cellsRow: this.map){
			for(ServerCell serverCell : cellsRow){
				for(Tile tile: serverCell.getTiles()){
					if(tile instanceof Gear){
						Gear gear = (Gear) tile;
						if(gear.getOrientations().get(0) == Orientation.CLOCKWISE){
							clockwiseGears.add(serverCell);
						}
					}
				}
			}
		}
		return clockwiseGears;
	}

	public ArrayList<ServerCell> getCounterclockwiseGears(){
		ArrayList<ServerCell> counterclockwiseGears = new ArrayList<>();
		for(ArrayList<ServerCell> cellsRow: this.map){
			for(ServerCell serverCell : cellsRow){
				for(Tile tile: serverCell.getTiles()){
					if(tile instanceof Gear){
						Gear gear = (Gear) tile;
						if(gear.getOrientations().get(0) == Orientation.COUNTERCLOCKWISE){
							counterclockwiseGears.add(serverCell);
						}
					}
				}
			}
		}
		return counterclockwiseGears;
	}

	public ArrayList<ServerCell> getBoardLaser(){
		ArrayList<ServerCell> boardLaser = new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map){
			for (ServerCell serverCell : cellsRow){
				for (Tile tile: serverCell.getTiles()){
					if (tile instanceof Laser){
						boardLaser.add(serverCell);
					}
				}
			}
		}
		return boardLaser;
	}

	public ArrayList<ServerCell> getEnergySpace(){
		ArrayList<ServerCell> energySpace =new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map){
			for (ServerCell serverCell : cellsRow){
				for (Tile tile: serverCell.getTiles()){
					if (tile instanceof EnergySpace){
						energySpace.add(serverCell);
					}
				}
			}
		}
		return energySpace;
	}

	public ArrayList<ServerCell> getCheckPoint(){
		ArrayList<ServerCell> checkPoint =new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map){
			for (ServerCell serverCell : cellsRow){
				for (Tile tile: serverCell.getTiles()){
					if (tile instanceof CheckPoint){
						checkPoint.add(serverCell);
					}
				}
			}
		}
		return checkPoint;
	}

	public ArrayList<ServerCell> getWall(){
		ArrayList<ServerCell> walls =new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map){
			for (ServerCell serverCell : cellsRow){
				for (Tile tile: serverCell.getTiles()){
					if (tile instanceof Wall){
						walls.add(serverCell);
					}
				}
			}
		}
		return walls;
	}

	public ArrayList<ServerCell> getRebootPoint(){
		ArrayList<ServerCell> rebootPoint =new ArrayList<>();
		for (ArrayList<ServerCell> cellsRow: this.map){
			for (ServerCell serverCell : cellsRow){
				for (Tile tile: serverCell.getTiles()){
					if (tile instanceof RestartPoint){
						rebootPoint.add(serverCell);
					}
				}
			}
		}
		return rebootPoint;
	}

	public ServerCell get(int x, int y) {
		return map.get(x).get(y);
	}

	public Board getBoard() {
		return board;
	}

	private void initMap(ArrayList<ArrayList<Cell>> cells) {
		for (ArrayList<Cell> row: cells) {
			ArrayList<ServerCell> serverCells = new ArrayList<>();
			for (Cell cell: row) {
				serverCells.add(null);
			}
			map.add(serverCells);
		}
	}

	private void setCells(ArrayList<ArrayList<Cell>> cells) {
		initMap(cells);
		int x = 0;
		for (ArrayList<Cell> row: cells) {
			int y = 0;
			for (Cell cell: row) {
				map.get(x).set(y, new ServerCell(cell, x, y));
				y += 1;
			}
			x += 1;
		}
	}
}



