package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Animation;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;
import bb.roborally.server.game.tiles.Antenna;
import bb.roborally.server.game.tiles.Laser;
import bb.roborally.server.game.tiles.Tile;
import bb.roborally.server.game.tiles.Wall;

import java.io.IOException;
import java.util.ArrayList;

import static bb.roborally.server.game.Orientation.LEFT;

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
				if(tile instanceof Laser ) {
					if(((Laser) tile).getCount() == ActivationPhaseHandler.getRegister()){

						boolean shootingEnd = false;
						int counter = 0;
						int counterWall = game.getBoard().getWall().size();
						int counterRobot = game.getPlayerQueue().getUsers().size();

						int wallPositionX = game.getBoard().getWall().get(counterWall).getPosition().getX();
						int wallPositionY = game.getBoard().getWall().get(counterWall).getPosition().getY();

						ArrayList<Orientation> wallOrientation = game.getBoard().getWall().get(counterWall).getTile("Wall").getOrientations();

						int antennaPositionX = game.getBoard().getAntenna().get(0).getPosition().getX();
						int antennaPositionY = game.getBoard().getAntenna().get(0).getPosition().getY();

						int robotPositionX = game.getPlayerQueue().getUsers().get(counterRobot).getRobot().getPosition().getX();
						int robotPositionY = game.getPlayerQueue().getUsers().get(counterRobot).getRobot().getPosition().getY();
						int posX = laserCell.getPosition().getX();
						int posY = laserCell.getPosition().getY();
						int caseType = 0;


						while(counter <= laserList.size()) {
							if(tile.getOrientations().equals(LEFT) ) {
								for (posX = laserCell.getPosition().getX(); posX >= 0; posX--) {
									if(posY == robotPositionY) {
										while (!shootingEnd) {

											if (posX == robotPositionX) {
												caseType = 1;
											}
											if (antennaPositionX > robotPositionX) {
												caseType = 2;
											}
											if (wallPositionX > robotPositionX) {
												caseType = 3;
											}
											if (wallPositionX == robotPositionX && (wallOrientation.equals(tile.getOrientations()))) {
												caseType = 4;
											}
										}
											switch(caseType) {
												case 1://shoot
													Spam spam = new Spam();
													game.getPlayerQueue().getUsers().get(counter).getProgrammingDeck().addCard(spam,true);
													game.getBoard().getRebootPoint().get(0).getPosition();
													game.getPlayerQueue().getUsers().get(counter).pl

															/*
															neue spam card
															add in discard pile d. players
															robot || player in rebootPoint && rebootQueue

															 */
													break;
												case 2://!shoot

													break;
												case 3://!shoot
													break;
												case 4://shoot
													break;
												default:

											}
											shootingEnd = true;
										}
									}

								}
							}
						}

					for (int i = 0; i<2; i++ ){
						user.getProgrammingDeck().getDiscardPile().add(game.getSpamDeck().getSpamDeck().remove(0));
					}
					PlayingCard playedCardisTrojan = user.getProgram().getCardInRegister(register);
					game.getTrojanDeck().getTrojanDeck().add(playedCardisTrojan);
					user.getProgram().resetOneRegister(register);


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

								counter += 1;
				}
							/*
	+1.getLaserPosition with !!correct register!! from activationPhaseHandler
	2.
	3. get  Orientation
	4. if RIGHT iterate over x += 1; coordinate until robot or wall or antenna isOnTile,
	 	if robot and wall and wall Orientation != RIGHT shoot Robot
		else break
	6. repeat for each Orientation
	 */


							//public Movement blockGameAction(Robot robot){
							//    if( robot.getPosition() == wallPosition){
							//        if(wallOrientation.contains(robot.getRobotOrientation())){
//
							//        }
							//    }
							//    return new Movement(robot.getClientID(),robot.getPosition().getColumn(),robot.getPosition().getRow());
							//}

							while (!shootingEnd) {


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

								}
							}
						}
					}
				}
			}
		}




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

