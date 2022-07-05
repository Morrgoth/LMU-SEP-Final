package bb.roborally.gui;

import bb.roborally.data.messages.*;
import bb.roborally.data.messages.connection.HelloServer;
import bb.roborally.data.messages.connection.Welcome;
import bb.roborally.game.User;
import bb.roborally.gui.data.RoboRallyModel;
import bb.roborally.gui.game.GameView;
import bb.roborally.gui.game.GameViewModel;
import bb.roborally.gui.start_menu.StartMenuView;
import bb.roborally.gui.start_menu.StartMenuViewModel;
import bb.roborally.networking.MessageHandler;
import bb.roborally.networking.NetworkConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RoboRally extends Application {

    private final String IP = "localhost"; // how should this be set?
    private final int PORT = 6868; // how should this be set?
    Stage primaryStage;
    RoboRallyModel roboRallyModel;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.roboRallyModel = new RoboRallyModel();
        StartMenuView startMenuView = new StartMenuView(primaryStage);
        StartMenuViewModel startMenuViewModel = new StartMenuViewModel(this, roboRallyModel, startMenuView);
        Scene scene = new Scene(startMenuView.getParent(), 900, 600);
        this.primaryStage.setTitle("RoboRally");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        connect();
    }

    @Override
    public void stop() {
        // Handle closing of the window
    }

    public void connect() {
        try {
            Socket socket = new Socket(IP, PORT);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            String helloClientJson = dataInputStream.readUTF();
            Envelope helloClientEnvelope = Envelope.fromJson(helloClientJson);
            if (helloClientEnvelope.getMessageType() == Envelope.MessageType.HELLO_CLIENT) {
                HelloServer helloServer = new HelloServer(false);
                dataOutputStream.writeUTF(helloServer.toJson());
                String welcomeJson = dataInputStream.readUTF();
                Envelope welcomeEnvelope = Envelope.fromJson(welcomeJson);
                if (welcomeEnvelope.getMessageType() == Envelope.MessageType.WELCOME) {
                    Welcome welcome = (Welcome) welcomeEnvelope.getMessageBody();
                    roboRallyModel.getPlayerRegistry().setLoggedInUserClientId(welcome.getClientID());
                    NetworkConnection.getInstance().initialize(socket, dataInputStream, dataOutputStream);
                    MessageHandler messageHandler = new MessageHandler(roboRallyModel);
                    messageHandler.start();
                    // The connection is ready
                } else {
                    // Error: not the correct message type
                }
            } else {
                // Error: not the correct message type
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openStartMenuView() {
        StartMenuView startMenuView = new StartMenuView(this.primaryStage);
        StartMenuViewModel startMenuViewModel = new StartMenuViewModel(this, roboRallyModel, startMenuView);
        this.primaryStage.getScene().setRoot(startMenuView.getParent());
    }

    public void openGameView() {
        GameView gameView = new GameView(primaryStage);
        GameViewModel gameViewModel = new GameViewModel(roboRallyModel, gameView);
        this.primaryStage.getScene().setRoot(gameView.getParent());
    }

    public static void main(String[] args) {
        launch();
    }
}