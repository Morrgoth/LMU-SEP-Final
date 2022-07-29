package bb.roborally.client.popup;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * A class for managing Popups.
 * @author Bence Ament
 */
public class Popup {

    private static Popup popup = null;

    private final PopupView popupView = new PopupView();

    private Popup() {

    }

    public static Popup getInstance() {
        if (popup == null) {
            popup = new Popup();
        }
        return popup;
    }

    public static void init(Stage stage) {
        getInstance().popupView.setStage(stage);
    }

    public static void open(Parent view) {
        getInstance().popupView.pushView(view);
        getInstance().popupView.show();
    }

    public static void close() {
        getInstance().popupView.popView();
        getInstance().popupView.hide();
    }
}
