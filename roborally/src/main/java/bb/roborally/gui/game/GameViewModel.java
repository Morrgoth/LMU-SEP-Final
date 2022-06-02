package bb.roborally.gui.game;

import bb.roborally.gui.RoboRally;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameViewModel {
    private GameModel model;
    private GameView view;
    private RoboRally roboRally;

    public GameViewModel(RoboRally roboRally, GameModel gameModel, GameView gameView) {
        this.roboRally = roboRally;
        model = gameModel;
        view = gameView;
        view.getChatListView().setItems(model.getChatMessages());
        setUpListeners();
    }

    private void setUpListeners() {

        view.getChatListView().getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                view.getChatListView().scrollTo(view.getChatListView().getItems().size() - 1);
            }
        });
        view.getSendButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String message = view.getMessageField().getText();
                model.setCurrentMessage(message);
                view.getMessageField().setText("");
            }
        });

        view.getMessageField().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String message = view.getMessageField().getText();
                    model.setCurrentMessage(message);
                    view.getMessageField().setText("");
                }
            }
        });
    }

}
