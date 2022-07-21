package bb.roborally.client.card;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardView {

    private final VBox view = new VBox();
    private final ImageView cardImageView = new ImageView();
    private final Label cardLabel = new Label();

    public CardView() {
        view.getChildren().addAll(cardImageView, cardLabel);
        view.setAlignment(Pos.CENTER);
    }

    public VBox getView() {
        return view;
    }

    public ImageView getCardImageView() {
        return cardImageView;
    }

    public Label getCardLabel() {
        return cardLabel;
    }

    public void setCardImageView(String path) {
        cardImageView.setImage(new Image(getClass().getResource(path).toExternalForm(), 30, 40, false, false));
    }
}
