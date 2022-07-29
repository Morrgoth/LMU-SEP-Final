package bb.roborally.client.popup_damage;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Zeynab Baiani
 * @author Muqiu Wang
 */
public class DamageView {
    private final VBox view = new VBox();
    Label label = new Label("Pick damage(s):");
    private final ComboBox<String> damageComboBox1 = new ComboBox<>();
    private final ComboBox<String> damageComboBox2 = new ComboBox<>();
    private final Button submitDamage = new Button("Submit");
    private final int count;
    private final String[] availablePiles;

    public DamageView(int count, String[] availablePiles) {
        this.count = count;
        this.availablePiles = availablePiles;
        if (count == 1) {
            damageComboBox1.setItems(FXCollections.observableArrayList(availablePiles[0]));
            view.getChildren().addAll(label, damageComboBox1, submitDamage);
        } else {
            damageComboBox1.setItems(FXCollections.observableArrayList(availablePiles[0]));
            damageComboBox2.setItems(FXCollections.observableArrayList(availablePiles[1]));
            view.getChildren().addAll(label, damageComboBox1, damageComboBox2, submitDamage);
        }
        view.setAlignment(Pos.CENTER);
        view.setSpacing(20);
        view.setStyle("-fx-background-color: #ffffff;");
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
        if (count == 1) {
            return new String[]{getSelectedDamage1()};
        } else {
            return new String[]{getSelectedDamage1(), getSelectedDamage2()};
        }
    }

    public Button getSubmitDamage() {
        return submitDamage;
    }

    public int getCount() {
        return count;
    }

    public String[] getAvailablePiles() {
        return availablePiles;
    }
}
