package bb.roborally.client.game;

import bb.roborally.client.board.BoardView;
import bb.roborally.client.chat.ChatView;
import bb.roborally.client.phase_info.PhaseInfoView;
import bb.roborally.client.player_list.PlayerListView;
import bb.roborally.client.programming_interface.ProgrammingInterfaceView;
import bb.roborally.client.timer.TimerView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;

public class GameView {
    private final GridPane view = new GridPane();
    private final TimerView timer = new TimerView();
    private final PhaseInfoView phase = new PhaseInfoView();
    private final ChatView chat = new ChatView();
    private final PlayerListView players = new PlayerListView();
    private final BoardView boardView = new BoardView();
    private final HBox controlBox = new HBox();
    private final ProgrammingInterfaceView programmingInterface = new ProgrammingInterfaceView();

    public GameView() {
        TabPane tabPane = new TabPane();
        Tab chatTab = new Tab("Chat", chat.getView());
        Tab playersTab = new Tab("Players", players.getView());
        tabPane.getTabs().addAll(chatTab, playersTab);
        VBox leftSide = new VBox(boardView.getGameBoard(), controlBox);
        VBox rightSide = new VBox(timer.getView(), phase.getView(), tabPane);
        leftSide.setSpacing(20);
        rightSide.setSpacing(10);
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(70);
        view.getColumnConstraints().add(column);
        column = new ColumnConstraints();
        column.setPercentWidth(30);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        view.getRowConstraints().add(row);
        view.getColumnConstraints().add(column);
        view.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        view.addColumn(0,leftSide);
        view.addColumn(1,rightSide);
        applyStyle();
    }

    private void applyStyle() {
        view.setPadding(new Insets(10, 10, 10, 10));
        view.setStyle("-fx-background-color:linear-gradient(to bottom, #386D8B, #494986, #638395)");
        view.setHgap(20);
        controlBox.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87)");
        controlBox.setAlignment(Pos.CENTER);
    }

    public Parent getView() {
        return view;
    }
    public TimerView getTimer() {
        return timer;
    }
    public PhaseInfoView getPhase() {
        return phase;
    }
    public BoardView getGameBoardView() {
        return boardView;
    }
    public ProgrammingInterfaceView getProgrammingInterface() {
        return programmingInterface;
    }
    public ChatView getChat() {
        return chat;
    }
    public PlayerListView getPlayers() {
        return players;
    }

    public void setControlToProgrammingInterface() {
        controlBox.getChildren().clear();
        controlBox.getChildren().add(programmingInterface.getView());
    }
    public void setControlToUpgradeShop() {
        //
    }
}
