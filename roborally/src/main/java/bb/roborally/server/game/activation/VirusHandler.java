package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.DrawDamage;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.protocol.gameplay.ReplaceCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Robot;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Spam;


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

    public void handle() throws IOException{
        Robot robot = user.getRobot();
        ArrayList<Robot> robots = new ArrayList<>();
        ArrayList<User> otherUsers = game.getPlayerQueue().getUsers();
        otherUsers.remove(user);

        for (User user : otherUsers) {                                      //calculating the distance of other robots to the one with the virus card

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
            for (User user : otherUsers) {
                if (user.getRobot().equals(robotInDistance)) {
                    Spam spam = (Spam) game.getSpamDeck().drawSpamCard();
                    spam.setDiscarded(true);
                    user.getPlayerInventory().getProgrammingDeck().addCard(spam);
                }
            }
        }

        PlayingCard playedCardIsVirus = user.getProgram().getCardInRegister(register);
        game.getVirusDeck().getVirusDeck().add(playedCardIsVirus);
        user.getProgram().resetOneRegister(register);

        user.getProgram().add(user.getProgrammingDeck().draw(),register);
        if (user.getProgram().getCardInRegister(register).getName().equals("Move1")){
            ReplaceCard replaceCard = new ReplaceCard(register,"Move1", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            Move1Handler move1Handler = new Move1Handler(server,game, user);
            move1Handler.handle();
        } else if (user.getProgram().getCardInRegister(register).getName().equals("Move2")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Move2", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            Move2Handler move2Handler = new Move2Handler(server,game,user);
            move2Handler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Move3")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Move3", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            Move3Handler move3Handler = new Move3Handler(server,game,user);
            move3Handler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Again")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Again", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            AgainHandler againHandler = new AgainHandler(server,game,user,register);
            againHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("BackUp")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"BackUp", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            BackUpHandler backUpHandler = new BackUpHandler(server,game, user);
            backUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("PowerUp")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"PowerUp", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            PowerUpHandler powerUpHandler = new PowerUpHandler(server,game,user);
            powerUpHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnLeft")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"TurnLeft", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            TurnLeftHandler turnLeftHandler = new TurnLeftHandler(server,game,user);
            turnLeftHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TurnRight")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"TurnRight", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            TurnRightHandler turnRightHandler = new TurnRightHandler(server,game,user);
            turnRightHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("UTurn")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"UTurn", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            UTurnHandler uTurnHandler = new UTurnHandler(server,game,user);
            uTurnHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Spam")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Spam", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            SpamHandler spamHandler = new SpamHandler(server,game, user, register);
            spamHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("TrojanHorse")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"TrojanHorse", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            TrojanHandler trojanHandler = new TrojanHandler(server, game, user, register);
            trojanHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Worm")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Worm", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            WormHandler wormHandler = new WormHandler(server,game,user,register);
            wormHandler.handle();
        }else if (user.getProgram().getCardInRegister(register).getName().equals("Virus")) {
            ReplaceCard replaceCard = new ReplaceCard(register,"Virus", user.getClientID());
            //server.broadcastOnly(replaceCard, user.getClientID());
            VirusHandler virusCardHandler = new VirusHandler(server,game,user,register);
            virusCardHandler.handle();
        }

        DrawDamage drawDamage = new DrawDamage(user.getClientID(), new String[] {"Spam"});
        server.broadcast(drawDamage);

        PlayCard playCard = new PlayCard("Virus");
        //server.broadcastOnly(playCard, user.getClientID());

        CardPlayed cardPlayed = new CardPlayed(user.getClientID(), "Virus");
        server.broadcastExcept(cardPlayed, user.getClientID());
    }
}

