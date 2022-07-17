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

    private static final Logger LOGGER = Logger.getLogger(RoboRally.class.getName());
    private final boolean localMode = false;
    private final RoboRallyModel roboRallyModel = new RoboRallyModel();
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    @Override
    public void start(Stage stage) throws IOException {
        LoaderView loaderView = new LoaderView();
        Scene scene = new Scene(loaderView.getView(), 900, 600);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setTitle("RoboRally");
        stage.setScene(scene);
        stage.show();
        setupLogger();
        ViewManager.init(stage, roboRallyModel);
        Popup.init(stage);
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
                    Socket socket = new Socket(getIp(), getPort());
                    inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outputStream = new PrintWriter(socket.getOutputStream(), true);
                    if (!socket.isClosed()) {
                        NetworkConnection.getInstance().initialize(socket, inputStream, outputStream);
                        MessageHandler messageHandler = new MessageHandler(roboRallyModel);
                        messageHandler.start();
                        timer.cancel();
                    }
                } catch (IOException e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 2500);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void setupLogger(){
        LOGGER.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("log/client.log");
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    private String getIp() {
        final String IP = "localhost";
        final String UNI_IP = "sep21.dbs.ifi.lmu.de";
        if (localMode) {
            return IP;
        } else {
            return UNI_IP;
        }
    }

    private int getPort() {
        int PORT = 6868;
        int UNI_PORT = 52019    ;// 1.0: 52018 52019; 2.0: 52020, 52021
        if (localMode) {
            return PORT;
        } else {
            return UNI_PORT;
        }
    }
}