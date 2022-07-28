package bb.roborally.client.popup_damage;

import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.popup.Popup;
import bb.roborally.protocol.game_events.SelectedDamage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DamageViewModel {
    private final RoboRallyModel roboRallyModel;
    private DamageView damageView;
    public DamageViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect(DamageView damageView){
        this.damageView = damageView;
        observeModelAndUpdate();
        setupListeners();
    }

    public void observeModelAndUpdate(){
        //damageView.getDamageComboBox1().setItems(roboRallyModel.getDamage());
        roboRallyModel.getPlayerQueue().getLocalPlayer().pickingDamageProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if(newVal){
                    damageView.getDamageComboBox1().setDisable(false);
                } else{
                    damageView.getDamageComboBox1().setDisable(true);
                }
            }
        });

        //damageView.getDamageComboBox2().setItems(roboRallyModel.getDamage());
        roboRallyModel.getPlayerQueue().getLocalPlayer().pickingDamageProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if(newVal){
                    damageView.getDamageComboBox2().setDisable(false);
                } else{
                    damageView.getDamageComboBox2().setDisable(true);
                }
            }
        });
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
