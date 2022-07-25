package bb.roborally.client.player_list;

import bb.roborally.client.card.CardView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class PlayerView {

    private final GridPane container = new GridPane();
    private final ImageView robotImageView = new ImageView();
    private final Label robotNameLabel = new Label();
    private final Label playerNameLabel = new Label();
    private final Label checkpointLabel = new Label();
    private final CardView currentCardView = new CardView();

    public PlayerView() {
        ImageView robotBackground = new ImageView(getClass().getResource("/extra/background.png").toExternalForm());
        StackPane robotStackPane = new StackPane(robotBackground, robotImageView);
        VBox robotContainer = new VBox(robotStackPane, robotNameLabel);
        Label checkPointText = new Label("Checkpoints: ");
        HBox checkPointContainer = new HBox(checkPointText, checkpointLabel);
        VBox playerInfoContainer = new VBox(playerNameLabel, checkPointContainer);
        VBox currentCardContainer = new VBox(currentCardView.getView());

        robotContainer.setAlignment(Pos.CENTER);
        robotImageView.setFitWidth(30);
        robotImageView.setFitHeight(30);

        ColumnConstraints constraints1 = new ColumnConstraints();
        constraints1.setPercentWidth(20);
        ColumnConstraints constraints2 = new ColumnConstraints();
        constraints2.setPercentWidth(20);
        ColumnConstraints constraints3 = new ColumnConstraints();
        constraints3.setPercentWidth(20);
        container.setMaxWidth(400);

        container.getColumnConstraints().addAll(constraints1, constraints2, constraints3);
        container.addColumn(0, robotContainer);
        container.addColumn(1, playerInfoContainer);
        container.addColumn(2, currentCardContainer);
    }

    public Parent getView() {
        return container;
    }

    public ImageView getRobotImageView() {
        return robotImageView;
    }

    public Label getRobotNameLabel() {
        return robotNameLabel;
    }

    public Label getPlayerNameLabel() {
        return playerNameLabel;
    }

    public Label getCheckpointLabel() {
        return checkpointLabel;
    }

    public CardView getCurrentCardView() {
        return currentCardView;
    }

}
