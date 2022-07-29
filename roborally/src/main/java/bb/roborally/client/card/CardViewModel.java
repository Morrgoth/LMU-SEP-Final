package bb.roborally.client.card;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Bence Ament
 */
public class CardViewModel {

    private final Card card;
    private CardView view;

    public CardViewModel(Card card) {
        this.card = card;
    }

    public void connect(CardView view) {
        this.view = view;
        observeModelAndUpdate();
        setupListeners();
    }

    private void observeModelAndUpdate() {
        view.getCardLabel().textProperty().bind(card.cardNameProperty());
        card.cardNameProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                view.setCardImageView(card.getResourcePath());
            }
        });
    }

    private void setupListeners() {

    }
}
