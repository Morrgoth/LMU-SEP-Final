module roborally.roborally {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens roborally.roborally to javafx.fxml;
    exports roborally.roborally;
}