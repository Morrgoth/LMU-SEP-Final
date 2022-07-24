package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.Position;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.Spam;

import java.util.ArrayList;

public class RebootHandler {

    //private static RebootHandler rebootHandler = null;
    ArrayList<User> users = new ArrayList<>();
    Server server;
    Game game;
    User user;

    //Board board;


    public RebootHandler(Server server, Game game, User user) {
        this.server = server;
        this.game = game;
        this.user = user;

    }

    public void handle() {
    }

    /*
    public void init(Server server, Game game) {
    }
    */

    public void addUser(User user) {
        user.setMustReboot(true);
        users.add(user);
    }

    /*
        check user startingPointgetX()
        check board.boardName -> set case accordingly
        compare getX to case -> if equal -> set rebootposition.equals(StartingPoint)
        add 2 spam Cards
        else remove to reboot queue.
     */
    Spam spam = new Spam();

    /*
    optional:
        while(users.size() != 0) -> reboot;
     */
    public void reboot() {

        addUser(user);
        Position startingPoint = user.getStartingPoint();
        int startingX = users.get(0).getStartingPointX();
        int clientID = users.get(0).getClientID();
        int boardCase = 0;

        if (game.getSelectedMap().equals("DeathTrap") && startingX >= 10) {
            boardCase = 1;
        }
        if (game.getSelectedMap().equals("DizzyHighway") && startingX <= 2) {
            boardCase = 2;
        }
        if (game.getSelectedMap().equals("ExtraCrispy") && startingX <= 2) {
            boardCase = 3;
        }
        if (game.getSelectedMap().equals("LostBearings") && startingX <= 2) {
            boardCase = 4;
        }
        if (game.getSelectedMap().equals("Twister") && startingX <= 2) {
            boardCase = 5;
        }


        switch (boardCase) {
            case 1:
                if(user.getRobot().getPosition().getX() < 10){
                    user.getRobot().setPosition(game.getBoard().getRebootPoint().get(0).getPosition());
                }else{
                    user.getRobot().setPosition(startingPoint);
                    //game.getRobotList().getRobotByFigureId(clientID).setPosition(startingPoint);
                }
                break;
            case 2, 3, 4, 5:
                if(user.getRobot().getPosition().getX() > 2){
                    user.getRobot().setPosition(game.getBoard().getRebootPoint().get(0).getPosition());
                }else{
                    user.getRobot().setPosition(startingPoint);
                    //game.getRobotList().getRobotByFigureId(clientID).setPosition(startingPoint);
                }
                break;
            }

        for (int i = 0; i < 2; i++) {
            game.getPlayerQueue().getUsers().get(clientID).getProgrammingDeck().addCard(spam, true);
            server.broadcast(new DrawDamage(clientID, "Spam"));
        }
        user.setMustReboot(false);
        users.remove(0);
    }
}



