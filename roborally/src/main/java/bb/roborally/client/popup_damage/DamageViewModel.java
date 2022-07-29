package bb.roborally.client.popup_damage;

import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.popup.Popup;
import bb.roborally.protocol.game_events.SelectedDamage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Zeynab Baiani
 * @author Muqiu Wang
 */
public class DamageViewModel {
    private DamageView damageView;
    public DamageViewModel() {

    }

    public void connect(DamageView damageView) {
        this.damageView = damageView;
        setupListeners();
    }

    public void setupListeners(){
        damageView.getSubmitDamage().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NetworkConnection.getInstance().send(new SelectedDamage(damageView.getSelectedDamages()));
                Popup.close();
            }
        });
    }

    public DamageView getDamageView() {
        return damageView;
    }
}
