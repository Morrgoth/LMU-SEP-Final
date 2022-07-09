package bb.roborally.client.game;

import bb.roborally.client.board.BoardView;
import bb.roborally.client.chat.ChatView;
import bb.roborally.client.phase_info.PhaseInfoView;
import bb.roborally.client.programming_interface.ProgrammingInterfaceView;
import bb.roborally.client.timer.TimerView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;

public class GameView {
    private final GridPane view = new GridPane();
    private final TimerView timer = new TimerView();
    private final PhaseInfoView phase = new PhaseInfoView();
    private final ChatView chat = new ChatView();
    private final BoardView boardView = new BoardView();
    private final HBox controlBox = new HBox();
    private final ProgrammingInterfaceView programmingInterface = new ProgrammingInterfaceView();

    public GameView() {
        VBox leftSide = new VBox(boardView.getGameBoard(), controlBox);
        VBox rightSide = new VBox(timer.getView(), phase.getView(), chat.getView());
        leftSide.setSpacing(20);
        rightSide.setSpacing(10);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(70);
        view.getColumnConstraints().add(column);

        column = new ColumnConstraints();
        column.setPercentWidth(30);
        view.getColumnConstraints().add(column);
        view.setPadding(new Insets(10, 10, 10, 10));
        view.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        view.addColumn(0,leftSide);
        view.addColumn(1,rightSide);
        applyStyle();
    }

    private void applyStyle() {
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

    public void setControlToProgrammingInterface() {
        controlBox.getChildren().clear();
        controlBox.getChildren().add(programmingInterface.getView());
    }
    public void setControlToUpgradeShop() {
        //
    }
}
