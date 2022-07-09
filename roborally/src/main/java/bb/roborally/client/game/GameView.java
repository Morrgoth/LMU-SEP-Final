package bb.roborally.client.game;

import bb.roborally.client.board.BoardView;
import bb.roborally.client.chat.ChatView;
import bb.roborally.client.phase_info.PhaseInfoView;
import bb.roborally.client.programming_interface.ProgrammingInterfaceView;
import bb.roborally.client.timer.TimerView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameView {
    private final GridPane view = new GridPane();
    private final TimerView timer = new TimerView();
    private final PhaseInfoView phase = new PhaseInfoView();
    private final ChatView chat = new ChatView();
    private final ProgrammingInterfaceView programmingInterface = new ProgrammingInterfaceView();
    private HBox controlBox = new HBox();
    private BoardView boardView = new BoardView();

    public BoardView getGameBoardView() {
        return boardView;
    }

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

    public void buildUI() {




        HBox program = new HBox();
        HBox upgrade = new HBox();
        HBox gameBoard = new HBox();
        controlBox = new HBox();
        VBox chatContainer = new VBox();
        chatContainer.getChildren().add(chat.getView());
        VBox cards = new VBox();
        cards.getChildren().addAll(program,upgrade);




        boardView = new BoardView();
        VBox leftSide = new VBox(boardView.getGameBoard(),cards, controlBox);
        //view.addColumn(1,rightSide);
        view.addColumn(0,leftSide);

        //timer.setAlignment(Pos.CENTER);
        //phase.setAlignment(Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        program.setAlignment(Pos.CENTER);
        upgrade.setAlignment(Pos.CENTER);

        //rightSide.setAlignment(Pos.BOTTOM_RIGHT);
        leftSide.setAlignment(Pos.TOP_LEFT);


        //timer.setPrefHeight(50);
        //phase.setPrefHeight(50);
        gameBoard.setPrefHeight(300);
        gameBoard.setPrefWidth(500);
        //rightSide.setSpacing(20);
        //rightSide.setPrefWidth(300);
        leftSide.setSpacing(20);
        leftSide.setPrefWidth(600);
        //rightSide.setPadding(new Insets(20,20,20,20));
        leftSide.setPadding(new Insets(20, 20, 20, 20));

        //chatContainer.setStyle("-fx-background-color: #FFFFFF");

        //
        gameBoard.setStyle("-fx-background-color: #FFFFFF");
        program.setStyle("-fx-background-color: rgba(214, 214, 231, 0.87);");
        upgrade.setStyle("-fx-background-color: #D6D6E7");

        //("-fx-background-color:linear-gradient(to left, #3b8d99, #6b6b83, #aa4b6b)");
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
