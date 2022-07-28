package bb.roborally.client.popupDamage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;

public class DamageView {
    private final VBox view = new VBox();
    Label label = new Label("Pick damage(s):");
    private final ComboBox<String> damageComboBox1 = new ComboBox<>();
    private final ComboBox<String> damageComboBox2 = new ComboBox<>();
    private final Button submitDamage = new Button("submit");

    public DamageView(){
        view.getChildren().addAll(label, damageComboBox1, damageComboBox2, submitDamage);
        view.setAlignment(Pos.CENTER);
        view.setSpacing(20);
    }

    public VBox getView() {
        return view;
    }

    public ComboBox<String> getDamageComboBox1() {
        return damageComboBox1;
    }

    public ComboBox<String> getDamageComboBox2() {
        return damageComboBox2;
    }

    public String getSelectedDamage1(){
        return damageComboBox1.getValue();
    }

    public String getSelectedDamage2(){
        return damageComboBox2.getValue();
    }
    public String[] getSelectedDamages(){
        return new String[]{getSelectedDamage1(), getSelectedDamage2()};
    }

    public Button getSubmitDamage() {
        return submitDamage;
    }
}
