package bb.roborally.client;

import bb.roborally.client.notification.Notification;
import bb.roborally.protocol.Envelope;
import bb.roborally.protocol.connection.HelloServer;
import bb.roborally.protocol.connection.Welcome;
import bb.roborally.client.game.GameView;
import bb.roborally.client.game.GameViewModel;
import bb.roborally.client.start_menu.StartMenuView;
import bb.roborally.client.start_menu.StartMenuViewModel;
import bb.roborally.client.networking.MessageHandler;
import bb.roborally.client.networking.NetworkConnection;
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
        this.primaryStage.setMinWidth(900);
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setMaxWidth(1000);
        this.primaryStage.setMaxHeight(700);
        this.primaryStage.setTitle("RoboRally");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        Notification.init(primaryStage, roboRallyModel.errorMessageProperty());
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
                }
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
        GameView gameView = new GameView();
        GameViewModel gameViewModel = new GameViewModel(roboRallyModel);
        gameViewModel.connect(gameView);
        this.primaryStage.getScene().setRoot(gameView.getView());
    }

    public static void main(String[] args) {
        launch();
    }
}