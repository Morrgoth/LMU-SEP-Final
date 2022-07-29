package bb.roborally.map;

import bb.roborally.protocol.Orientation;
import bb.roborally.protocol.map.Board;
import bb.roborally.protocol.map.Cell;
import bb.roborally.protocol.map.GameStarted;
import bb.roborally.protocol.map.tiles.*;

import java.util.ArrayList;

/**
 * @author Philipp Keyzman
 */
public class LostBearingsBuilder implements BoardBuilder {

	@Override
	public GameStarted build() {

		int xAxis = 13;
		int yAxis = 10;
		int maxCellContent = 4;

		ArrayList<ArrayList<Cell>> lostBearings = new ArrayList<>();

		//for - Schleife x-Koordinaten (äußerste ArrayList)
		for (int x = 0; x < xAxis; x++) {
			lostBearings.add(new ArrayList<>());

			//for - Schleife y-Koordinaten (mittlere ArrayList)
			for (int y = 0; y < yAxis; y++) {
				lostBearings.get(x).add(new Cell());

				//for - Schleife Cells (innerste ArrayList)
				for (int k = 0; k < maxCellContent; k++) {

					if (x == 0 && y == 0) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							RestartPoint restartPoint = new RestartPoint("A", orientations);
							lostBearings.get(x).get(y).addTile(restartPoint);
						}
					}

					if ((x == 0 && y == 3)) {
						if (k == 0) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(x).get(y).addTile(startPoint);
						}
					}
					if ((x == 0 && y == 6)) {
						if (k == 0) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(x).get(y).addTile(startPoint);
						}
					}
					if ((x == 1 && y == 1)) {
						if (k == 0) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(x).get(y).addTile(startPoint);
						}
					}
					if ((x == 1 && y == 4)) {
						if (k == 0) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(x).get(y).addTile(startPoint);
						}
					}
					if ((x == 1 && y == 5)) {
						if (k == 0) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(x).get(y).addTile(startPoint);
						}
					}
					if ((x == 1 && y == 8)) {
						if (k == 0) {
							StartPoint startPoint = new StartPoint("A");
							lostBearings.get(x).get(y).addTile(startPoint);
						}
					}
					if (x == 0 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Antenna antenna = new Antenna("A", orientations);
							lostBearings.get(x).get(y).addTile(antenna);
						}
					}
					if (x == 1 && y == 2) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}
					if (x == 1 && y == 7) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}
					if (x == 2 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}
					if (x == 2 && y == 5) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}
					if (x == 9 && y == 3) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Laser laser = new Laser("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(laser);
						}
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}

					if (x == 9 && y == 6) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}
					if (x == 6 && y == 3) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}

					if (x == 6 && y == 6) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							Laser laser = new Laser("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(laser);
						}
						if (k == 1) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							Wall wall = new Wall("1A", orientations);
							lostBearings.get(x).get(y).addTile(wall);
						}
					}

					if (x == 6 && y == 2) {
						if (k == 0) {
							Pit pit = new Pit("1A");
							lostBearings.get(x).get(y).addTile(pit);
						}
					}
					if (x == 6 && y == 7) {
						if (k == 0) {
							Pit pit = new Pit("1A");
							lostBearings.get(x).get(y).addTile(pit);
						}
					}
					if (x == 9 && y == 2) {
						if (k == 0) {
							Pit pit = new Pit("1A");
							lostBearings.get(x).get(y).addTile(pit);
						}
					}
					if (x == 9 && y == 7) {
						if (k == 0) {
							Pit pit = new Pit("1A");
							lostBearings.get(x).get(y).addTile(pit);
						}
					}
					if (x == 7 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 8 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}


					if (x == 7 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}
					if (x == 8 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 4 && y == 5) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A", orientations, 2);
							lostBearings.get(x).get(y).addTile(checkPoint);
						}
					}
					if (x == 8 && y == 2) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A", orientations, 3);
							lostBearings.get(x).get(y).addTile(checkPoint);
						}
					}
					if (x == 8 && y == 7) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A", orientations, 4);
							lostBearings.get(x).get(y).addTile(checkPoint);
						}
					}
					if (x == 11 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							CheckPoint checkPoint = new CheckPoint("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(checkPoint);
						}
					}
					if (x == 5 && y == 2) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(energySpace);
						}
					}
					if (x == 5 && y == 7) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(energySpace);
						}
					}
					if (x == 10 && y == 2) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(energySpace);
						}
					}
					if (x == 10 && y == 7) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(energySpace);
						}
					}
					if (x == 7 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(energySpace);
						}
					}
					if (x == 8 && y == 5) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							EnergySpace energySpace = new EnergySpace("1A", orientations, 1);
							lostBearings.get(x).get(y).addTile(energySpace);
						}
					}
					if (x == 5 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.COUNTERCLOCKWISE);
							Gear gear = new Gear("1A", orientations);
							lostBearings.get(x).get(y).addTile(gear);
						}
					}
					if (x == 8 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.COUNTERCLOCKWISE);
							Gear gear = new Gear("1A", orientations);
							lostBearings.get(x).get(y).addTile(gear);
						}
					}
					if (x == 10 && y == 4) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.CLOCKWISE);
							Gear gear = new Gear("1A", orientations);
							lostBearings.get(x).get(y).addTile(gear);
						}
					}
					if (x == 5 && y == 5) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.CLOCKWISE);
							Gear gear = new Gear("1A", orientations);
							lostBearings.get(x).get(y).addTile(gear);
						}
					}
					if (x == 7 && y == 5) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.CLOCKWISE);
							Gear gear = new Gear("1A", orientations);
							lostBearings.get(x).get(y).addTile(gear);
						}
					}
					if (x == 10 && y == 5) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.COUNTERCLOCKWISE);
							Gear gear = new Gear("1A", orientations);
							lostBearings.get(x).get(y).addTile(gear);
						}
					}
					// 2/9
					if (x == 2 && y == 0) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 2 && y == 9) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 3 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 3 && y == 8) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 4 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 4 && y == 8) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 4 && y == 0) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 4 && y == 9) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 5 && y == 3) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 5 && y == 6) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 6 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 7 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 8 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 9 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 10 && y == 3) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.BOTTOM);
							orientations.add(Orientation.TOP);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 10 && y == 6) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 2, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 11 && y == 0) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 11 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 11 && y == 8) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 11 && y == 9) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.TOP);
							orientations.add(Orientation.BOTTOM);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 12 && y == 1) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.LEFT);
							orientations.add(Orientation.RIGHT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}
					if (x == 12 && y == 8) {
						if (k == 0) {
							ArrayList<Orientation> orientations = new ArrayList<>();
							orientations.add(Orientation.RIGHT);
							orientations.add(Orientation.LEFT);
							ConveyorBelt conveyorBelt = new ConveyorBelt("1A", 1, orientations);
							lostBearings.get(x).get(y).addTile(conveyorBelt);
						}
					}

					if (x == 1 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 5 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 6 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 7 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 8 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 9 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 10 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 0) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 0 && y == 1) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 2 && y == 1) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 5 && y == 1) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 10 && y == 1) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 0 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 2 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 4 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 7 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 11 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 2) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 1 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 2 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 4 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 11 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 3) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 4) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 4 && y == 4) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 6 && y == 4) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 9 && y == 4) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 4) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 0 && y == 5) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 5) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 6 && y == 5) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 9 && y == 5) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 11 && y == 5) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 5) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 1 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 2 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 4 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 11 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 6) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 0 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 2 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 4 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 7 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 11 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 7) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 0 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 2 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 5 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 6 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 7 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 8 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 9 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 10 && y == 8) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 0 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 1 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 3 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 5 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 6 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 7 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 8 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 9 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 10 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}

					if (x == 12 && y == 9) {
						if (k == 0) {
							Empty empty = new Empty("1A");
							lostBearings.get(x).get(y).addTile(empty);
						}
					}


				}
			}
		}
		return new GameStarted(new Board(lostBearings));
	}
}

/*
Antenna 		
BlackHole		
BoardLaser		
CheckPoint		
ConveyorBelt	
EnergySpace		
Floor 			
Gear			
PushPanel		
RebootPoint 	
Wall 			
 */