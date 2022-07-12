package bb.roborally.client;

import bb.roborally.client.game.GameView;
import bb.roborally.client.game.GameViewModel;
import bb.roborally.client.start_menu.StartMenuView;
import bb.roborally.client.start_menu.StartMenuViewModel;
import javafx.stage.Stage;

public class ViewManager {

    private static ViewManager viewManager = null;
    private Stage stage;
    private RoboRallyModel roboRallyModel;

    private ViewManager() {

    }

    public static ViewManager getInstance() {
        if (viewManager == null) {
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    public static void init(Stage stage, RoboRallyModel roboRallyModel) {
        getInstance();
        viewManager.stage = stage;
        viewManager.roboRallyModel = roboRallyModel;
    }

    public static void openStartMenuView() {
        StartMenuView startMenuView = new StartMenuView();
        StartMenuViewModel startMenuViewModel = new StartMenuViewModel(viewManager.roboRallyModel);
        startMenuViewModel.connect(startMenuView);
        viewManager.stage.getScene().setRoot(startMenuView.getView());
    }

    public static void openGameView() {
        GameView gameView = new GameView();
        GameViewModel gameViewModel = new GameViewModel(viewManager.roboRallyModel);
        gameViewModel.connect(gameView);
        viewManager.stage.getScene().setRoot(gameView.getView());
    }
}
