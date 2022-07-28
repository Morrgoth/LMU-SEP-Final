package bb.roborally.client.popupReboot;

import bb.roborally.client.RoboRallyModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RebootViewModel {
    private RebootView rebootView;
    private final RoboRallyModel roboRallyModel;

    private final ObservableList<String> rebootDirections = FXCollections.observableArrayList();
    public RebootViewModel() {
        rebootDirections.add("top");
        rebootDirections.add("bottom");
        rebootDirections.add("right");
        rebootDirections.add("left");
    }


    public RebootViewModel(RoboRallyModel roboRallyModel) {
        this.roboRallyModel = roboRallyModel;
    }

    public void connect (RebootView view){
        this.rebootView = view;

    }

    public void observeModelAndUpdate(){
        rebootView.getRebootCombo().setItems(roboRallyModel.getRebootDirections());
        roboRallyModel.getPlayerQueue().getLocalPlayer().rebootPropertyProperty().addListener();
    }

    public void setUpListeners(){

    }

}
