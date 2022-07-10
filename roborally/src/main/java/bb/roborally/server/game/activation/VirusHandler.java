package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;



import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Veronika Heckel
 */
public class VirusHandler {

    Server server;
    Game game;
    User user;
    int register;


    public VirusHandler(Server server, Game game, User user, int register) {
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }

    public void handle() {
        Robot robot = user.getRobot();
        ArrayList<Robot> robots = new ArrayList<>();

        for (User user : game.getPlayerQueue().getUsers()) {

            int distanceColumn = (user.getRobot().getPosition().getY() - robot.getPosition().getY());
            int distanceRow = (user.getRobot().getPosition().getX() - robot.getPosition().getX());
            int toPowerTwo = 2;

            int distanceColumnToPower = (int) Math.pow(distanceColumn, toPowerTwo);
            int distanceRowToPower = (int) Math.pow(distanceRow, toPowerTwo);

            int distance = distanceColumnToPower + distanceRowToPower;
            if (distance <= 36) {
                robots.add(user.getRobot());
            }

        }

        for (Robot robotInDistance : robots) {
            for (User user : game.getPlayerQueue().getUsers()) {
                if (user.getRobot().equals(robotInDistance)) {
                    user.getPlayerInventory().getProgrammingDeck().getDiscardPile().add(game.getVirusDeck().getVirusDeck().remove(0));
                }
            }


            PlayingCard playedCardIsVirus = user.getProgram().getCardInRegister(register);
            game.getVirusDeck().getVirusDeck().add(playedCardIsVirus);
            user.getProgram().resetOneRegister(register);


            DrawDamage drawDamage = new DrawDamage(user.getClientID(), "Virus");
            try {
                server.broadcast(drawDamage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            PlayCard playCard = new PlayCard("Virus");
            try {
                server.broadcastOnly(playCard, user.getClientID());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            CardPlayed cardPlayed = new CardPlayed(user.getClientID(), "Virus");
            try {
                server.broadcastExcept(cardPlayed, user.getClientID());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

