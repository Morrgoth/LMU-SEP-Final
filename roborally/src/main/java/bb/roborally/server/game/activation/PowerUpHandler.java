package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Energy;
import bb.roborally.protocol.gameplay.CardPlayed;
import bb.roborally.protocol.gameplay.PlayCard;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import bb.roborally.server.game.cards.PlayingCard;


import java.io.IOException;

public class PowerUpHandler {
    Server server;
    Game game;
    User user;
    PlayerInventory playerInventory;

    public PowerUpHandler(Server server, Game game, User user){
        this.user = user;
        this.game = game;
        this.server = server;
    }

    public void handle() throws IOException{
        user.getRobot().increaseEnergyCubeAmountBy(1);
        Energy energy = new Energy(user.getClientID(), user.getRobot().getEnergyCubeAmount(), "PowerUpCard");
        server.broadcast(energy);

        PlayCard playCard = new PlayCard("PowerUp");
        //server.broadcastOnly(playCard, user.getClientID());

        CardPlayed cardPlayed = new CardPlayed(user.getClientID(), "PowerUp");
        server.broadcastExcept(cardPlayed, user.getClientID());
    }
}
