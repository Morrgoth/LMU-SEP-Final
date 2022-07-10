package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Reboot;
import bb.roborally.protocol.game_events.RebootDirection;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.Orientation;
import bb.roborally.server.game.User;
import bb.roborally.server.game.cards.PlayingCard;
import bb.roborally.server.game.cards.Worm;

import java.io.IOException;

public class WormHandler {

    Server server;
    Game game;
    User user;

    int register;

    public WormHandler(Server server, Game game, User user, int register){
        this.server = server;
        this.game = game;
        this.user = user;
        this.register = register;
    }

    public void handle(){

        Reboot reboot = new Reboot(user.getClientID());
        try {
            server.broadcast(reboot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MovementCheck movementCheck = new MovementCheck();
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.TOP)){
            RebootDirection rebootDirection = new RebootDirection("top");
            try{
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.BOTTOM)){
            RebootDirection rebootDirection = new RebootDirection("bottom");
            try {
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.LEFT)){
            RebootDirection rebootDirection = new RebootDirection("left");
            try {
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(movementCheck.rebootPointStartOrientation(user).equals(Orientation.RIGHT)){
            RebootDirection rebootDirection = new RebootDirection("right");
            try {
                server.broadcast(rebootDirection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        PlayingCard playedCardIsWorm = user.getProgram().getCardInRegister(register);
        game.getVirusDeck().getVirusDeck().add(playedCardIsWorm);
        user.getProgram().resetOneRegister(register);


        PlayCard playCard = new PlayCard("Worm");
        try{
            server.broadcastOnly(playCard, user.getClientID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CardPlayed cardPlayed = new CardPlayed(user.getClientID(), "Worm");
        try {
            server.broadcastExcept(cardPlayed, user.getClientID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

