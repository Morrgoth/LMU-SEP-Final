package bb.roborally.client.popup;

import javafx.scene.Parent;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Bence Ament
 */
public class PopupView {

    private final Popup popup = new Popup();
    private Stage stage;

    public PopupView() {
        popup.centerOnScreen();
    }

    public Popup getPopup() {
        return popup;
    }

    public void pushView(Parent view) {
        popView();
        popup.getContent().add(view);
    }

    public void popView() {
        popup.getContent().clear();
    }

    public void show() {
        popup.show(stage);
    }

    public void hide() {
        popup.hide();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
