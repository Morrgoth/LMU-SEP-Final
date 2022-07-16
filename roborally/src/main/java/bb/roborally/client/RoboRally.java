package bb.roborally.client;

import bb.roborally.client.loader.LoaderView;
import bb.roborally.client.notification.Notification;
import bb.roborally.client.popup.Popup;
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
import java.util.Timer;
import java.util.TimerTask;

public class RoboRally extends Application {

    private final String IP = "localhost"; // how should this be set?
    private final int PORT = 6868; // how should this be set?
    Stage primaryStage;
    private final RoboRallyModel roboRallyModel = new RoboRallyModel();
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        LoaderView loaderView = new LoaderView();
        Scene scene = new Scene(loaderView.getView(), 900, 600);
        this.primaryStage.setMinWidth(900);
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setTitle("RoboRally");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        ViewManager.init(primaryStage, roboRallyModel);
        Popup.init(primaryStage);
        Notification.init(roboRallyModel.errorMessageProperty());
        connect();
    }

    @Override
    public void stop() {
        // Handle closing of the window
    }

    public void connect() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
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
                            ViewManager.openStartMenuView();
                            Welcome welcome = (Welcome) welcomeEnvelope.getMessageBody();
                            roboRallyModel.getPlayerQueue().setLocalPlayerId(welcome.getClientID());
                            NetworkConnection.getInstance().initialize(socket, dataInputStream, dataOutputStream);
                            MessageHandler messageHandler = new MessageHandler(roboRallyModel);
                            messageHandler.start();
                            timer.cancel();
                        }
                    }
                } catch (IOException e) {
                    // Logging
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    public static void main(String[] args) {
        launch();
    }
}