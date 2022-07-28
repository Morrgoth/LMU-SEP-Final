package bb.roborally.client.popupReboot;

import bb.roborally.client.RoboRallyModel;
import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.popup.Popup;
import bb.roborally.protocol.game_events.RebootDirection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class RebootViewModel {
    private RebootView rebootView;
    private final RoboRallyModel roboRallyModel;

    public RebootViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect (RebootView rebootView){
        this.rebootView = rebootView;
        observeModelAndUpdate();
        setUpListeners();
    }

    public void observeModelAndUpdate(){
        rebootView.getRebootCombo().setItems(roboRallyModel.getRebootDirections());
        roboRallyModel.getPlayerQueue().getLocalPlayer().rebootProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if(newVal){
                    rebootView.getRebootCombo().setDisable(false);
                }else {
                    rebootView.getRebootCombo().setDisable(true);
                }
            }
        });
    }

    public void setUpListeners(){
        rebootView.getSubmitButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NetworkConnection.getInstance().send(new RebootDirection(rebootView.getSelectedDirection()));
                Popup.close();
            }
        });
    }

    public RebootView getRebootView() {
        return rebootView;
    }
}
