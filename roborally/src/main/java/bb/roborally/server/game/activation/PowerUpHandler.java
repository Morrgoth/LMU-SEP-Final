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

    public void handle(){
        user.getPlayerInventory().setEnergy(playerInventory.getEnergy() + 1);
        Energy energy = new Energy(user.getClientID(), playerInventory.getEnergy(), "PowerUp");
        try{
            server.broadcast(energy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
