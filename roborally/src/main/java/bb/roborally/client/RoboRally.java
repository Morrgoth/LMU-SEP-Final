package bb.roborally.client;

import bb.roborally.client.login.LoginView;
import bb.roborally.client.login.LoginViewModel;
import bb.roborally.client.networking.MessageHandler;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.notification.Notification;
import bb.roborally.client.popup.Popup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Tolga Engin
 * @author Bence Ament
 * @author Zeynab Baiani
 * @author Muqiu Wang
 * @author Philip Keyzman
 * @author Veronika Heckel
 * The main class of the client
 */
public class RoboRally extends Application {

    private static final Logger LOGGER = Logger.getLogger(RoboRally.class.getName());
    private final RoboRallyModel roboRallyModel = new RoboRallyModel();
    private BufferedReader inputStream;
    private PrintWriter outputStream;

    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView();
        LoginViewModel loginViewModel = new LoginViewModel(this, roboRallyModel);
        loginViewModel.connect(loginView);
        Scene scene = new Scene(loginView.getView(), 900, 600);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setTitle("RoboRally");
        stage.setScene(scene);
        stage.show();
        setupLogger();
        ViewManager.init(stage, roboRallyModel);
        Popup.init(stage);
        Notification.init(roboRallyModel.errorMessageProperty());
        //connect();
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
                    Socket socket = new Socket(roboRallyModel.getIp(), roboRallyModel.getPort());
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
        timer.scheduleAtFixedRate(task, 0, 3000);
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
}