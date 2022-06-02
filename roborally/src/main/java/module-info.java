module roborally.roborally {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports bb.roborally.gui;
    opens bb.roborally.gui to javafx.fxml;
}