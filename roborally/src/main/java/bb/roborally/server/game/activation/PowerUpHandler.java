package bb.roborally.server.game.activation;

import bb.roborally.protocol.game_events.Energy;
import bb.roborally.server.Server;
import bb.roborally.server.game.*;
import javafx.geometry.Pos;

public class PowerUpHandler {
    Server server;
    Game game;
    User user;
    PlayerInventory playerInventory;
    EnergyCube energyCube;
    public PowerUpHandler(User user, Game game, Server server, PlayerInventory playerInventory, EnergyCube energyCube){
        this.user = user;
        this.game = game;
        this.server = server;
        this.playerInventory = playerInventory;
        this.energyCube = energyCube;
    }
}
