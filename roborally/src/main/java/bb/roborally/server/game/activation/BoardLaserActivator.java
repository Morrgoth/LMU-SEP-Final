package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.Laser;
import bb.roborally.server.game.tiles.Tile;
import bb.roborally.server.game.tiles.Wall;

import java.io.IOException;
import java.util.ArrayList;

public class BoardLaserActivator {
	private Server server;
	private Game game;

	public BoardLaserActivator(Server server,Game game){
		this.server = server;
		this.game = game;
	}

	public void activate(){
		Animation animation = new Animation("Laser");
		try{
			server.broadcast(animation);
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		ArrayList<Cell> laserList = game.getBoard().getBoardLaser();
		for(Cell laserCell : laserList){
			for(Tile tile : laserCell.getTiles()){
				if(tile instanceof Laser){
					if(((Laser) tile).getCount() == ActivationPhaseHandler.getRegister()){
						if(tile.getOrientations().equals(Orientation.LEFT) ){


							/*
	+1.getLaserPosition with !!correct register!! from activationPhaseHandler
	2.
	3. get  Orientation
	4. if RIGHT iterate over x += 1; coordinate until robot or wall or antenna isOnTile,
	 	if robot and wall and wall Orientation != RIGHT shoot Robot
		else break
	6. repeat for each Orientation
	7.
	 */
								int tileType;
								boolean shotEnd = false;
								int posX = laserCell.getPosition().getX();
								int posY = laserCell.getPosition().getY();
							while (!shotEnd) {


								if (laserCell.hasTile("Wall")) {
									tileType = 1;
								} else if (laserCell.hasTile("Antenna")) {
									tileType = 2;
								} else if (laserCell.hasTile("Wall") && laserCell.hasTile("Robot")) {
									//how do I get the wall orientation?!
											tileType =3;
								} else if (laserCell.hasTile("Robot") ) {


								}

								shotEnd = true;
								laserCell.setPosition(posX - 1, laserCell.getPosition().getY());

							}

								switch(tileType){
									case 1:

										break;
									case Antenna;
									break;
									case Robot;
									break;

							/*
							if (boardLaserOrientation.equals(Orientation.LEFT) && getBoardLaserPosition().getColumn() > Antenna.getAntennaPosition().getColumn()
									|| getBoardLaserPosition().getColumn() > Wall.getWallPosition().getColumn()) {

								if (PlayerInventory.getClientID() == robot.getClientID()) {
									PlayerInventory.addCard(SPAM_CARD);

									robot.setLasered();
									message.add(new DrawDamage(robot));
									message.add(new AddCard(robot));

							 */
								}
							}
						}
					}
				}
			}
		}
	}


	/////////////////
	public void activate() {
		//create animation BlueBelt
		Animation animation = new Animation("BlueBelt");
		//broadcast animation catch Exception
		try {
			server.broadcast(animation);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		//make Conveyorbelt be Conveyorbelt from game
		ArrayList<Cell> belts = game.getBoard().getBlueConveyorBelts();
		// for each user in gameQueue from game
		for (User user: game.getPlayerQueue().getUsers()) {
			//if NOT on tile & counter = 0
			boolean isOnTile = false;
			int counter = 0;
			//while counter <
			while (counter < belts.size() && !isOnTile) {
				if (belts.get(counter).getPosition().equals(user.getRobot().getPosition())) {
					isOnTile = true;
				}
				counter += 1;
			}
			if (isOnTile) {
				// TODO: move the Robot
			}
		}
	}

	//////////////////////

    /*

ShootingLogic to work on further

    public ArrayList<Message> shootLaser(BoardLaser boardLaser, Robot robot) {
        ArrayList <Message> message = new ArrayList<Message>();
        switch (count) {
            case 1:
                if (boardLaserOrientation.equals(Orientation.LEFT) && getBoardLaserPosition().getColumn() > Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() > Wall.getWallPosition().getColumn()) {

                    if (PlayerInventory.getClientID() == robot.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.RIGHT) && getBoardLaserPosition().getColumn() < Antenna.getAntennaPosition().getColumn()
                        || getBoardLaserPosition().getColumn() < Wall.getWallPosition().getColumn()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.TOP) && getBoardLaserPosition().getRow() > Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() > Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }
                if (boardLaserOrientation.equals(Orientation.BOTTOM) && getBoardLaserPosition().getRow() < Antenna.getAntennaPosition().getRow()
                        || getBoardLaserPosition().getRow() < Wall.getWallPosition().getRow()) {

                    if (robot.getClientID() == PlayerInventory.getClientID()) {
                        PlayerInventory.addCard(SPAM_CARD);

                        robot.setLasered();
                        message.add(new DrawDamage(robot));
                        message.add(new AddCard(robot));
                    }
                }

        }
        return message;
    }
*/
//////////////////////////
}

