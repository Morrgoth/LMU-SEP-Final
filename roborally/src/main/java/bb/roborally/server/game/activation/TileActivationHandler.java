package bb.roborally.server.game.activation;

import bb.roborally.server.Server;
import bb.roborally.server.game.Game;
import bb.roborally.server.game.User;

import java.io.IOException;
import java.util.ArrayList;

public class
TileActivationHandler {

    private Server server;
    private Game game;
    private int register;
    private ArrayList<User> alreadyOnBelts;

    public TileActivationHandler(Server server, Game game, int register, ArrayList<User> alreadyOnBelts) {
        this.server = server;
        this.game = game;
        this.register = register;
        this.alreadyOnBelts = alreadyOnBelts;
    }

    //Pit und RestartPointhandling implemented in MovementCheck-Klasse
    public void handle() throws IOException {
        BlueConveyorBeltActivator blueConveyorBeltActivator = new BlueConveyorBeltActivator(server, game, alreadyOnBelts);
        blueConveyorBeltActivator.activate();

        GreenConveyorBeltActivator greenConveyorBeltActivator = new GreenConveyorBeltActivator(server, game, alreadyOnBelts);
        greenConveyorBeltActivator.activate();

        PushPanelActivator pushPanelActivator = new PushPanelActivator(server, game, register);
        pushPanelActivator.activate();

        GearActivator gearActivator = new GearActivator(server, game);
        gearActivator.activate();

        BoardLaserActivator boardLaserActivator = new BoardLaserActivator(server, game, register);
        boardLaserActivator.activate();

        RobotLaserActivator robotLaserActivator = new RobotLaserActivator(server, game);
        robotLaserActivator.activate();

        EnergySpaceActivator energySpaceActivator = new EnergySpaceActivator(server, game);
        energySpaceActivator.activate();

        CheckPointActivator checkPointActivator = new CheckPointActivator(server, game);
        checkPointActivator.activate();
    }
}
