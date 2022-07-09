package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;

public class
TileActivationHandler {

    private Server server;
    private Game game;
    private int register;

    public TileActivationHandler(Server server, Game game, int register) {
        this.server = server;
        this.game = game;
        this.register = register;
    }

    //Pit und RestartPointhandling implemented in MovementCheck-Klasse
    public void handle() {
        BlueConveyorBeltActivator blueConveyorBeltActivator = new BlueConveyorBeltActivator(server, game);
        blueConveyorBeltActivator.activate();
        GreenConveyorBeltActivator greenConveyorBeltActivator = new GreenConveyorBeltActivator(server, game);
        greenConveyorBeltActivator.activate();
        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, register);
        pushPanelActivator.activate();
        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();
        // TODO: BoardLaserActivator
        // TODO: RobotLaserActivator
        // TODO: EnergySpaceActivator
        // TODO: CheckPointActivator
    }
}
