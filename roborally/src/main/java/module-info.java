module roborally.roborally {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.google.gson;

    exports bb.roborally.gui;
    opens bb.roborally.gui to javafx.fxml;
}