module roborally.roborally {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.logging;

    exports bb.roborally.gui;
    exports bb.roborally.data.messages;
    exports bb.roborally.data.messages.connection;
    exports bb.roborally.data.messages.chat;
    exports bb.roborally.data.messages.gameplay;
    exports bb.roborally.data.util;
    opens bb.roborally.gui to javafx.fxml;
}