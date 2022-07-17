package bb.roborally.client;

import bb.roborally.client.loader.LoaderView;
import bb.roborally.client.notification.Notification;
import bb.roborally.client.popup.Popup;
import bb.roborally.client.networking.MessageHandler;
import bb.roborally.client.networking.NetworkConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RoboRally extends Application {

    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());
    private final String UNI_IP = "sep21.dbs.ifi.lmu.de"; // how should this be set?
    private final int UNI_PORT = 52018; // 52019; 2.0: 52020, 52021
    private final String IP = "localhost";
    private final int PORT = 6868;
    private Stage primaryStage;

    private final RoboRallyModel roboRallyModel = new RoboRallyModel();
    //DataOutputStream dataOutputStream;
    //DataInputStream dataInputStream;
    private BufferedReader inputStream;
    private PrintWriter outputStream;

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
        setupLogger();
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
                    inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputStream = new PrintWriter(socket.getOutputStream(), true);
                    if (!socket.isClosed()) {
                        NetworkConnection.getInstance().initialize(socket, inputStream, outputStream);
                        MessageHandler messageHandler = new MessageHandler(roboRallyModel);
                        messageHandler.start();
                        timer.cancel();
                    }
                } catch (IOException e) {
                    //System.out.println(e.getMessage());
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void setupLogger(){
        LOGGER.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("client.log");
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}