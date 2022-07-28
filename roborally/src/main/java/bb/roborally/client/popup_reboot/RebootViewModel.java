package bb.roborally.client.popup_reboot;

import bb.roborally.client.networking.NetworkConnection;
import bb.roborally.client.popup.Popup;
import bb.roborally.protocol.game_events.RebootDirection;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class RebootViewModel {
    private RebootView rebootView;

    public RebootViewModel() {

    }

    public void connect (RebootView rebootView){
        this.rebootView = rebootView;
        observeModelAndUpdate();
        setUpListeners();
    }

    public void observeModelAndUpdate() {

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
