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
    exports bb.roborally.data.messages.game_events;
    exports bb.roborally.data.messages.gameplay;
    exports bb.roborally.data.messages.lobby;
    exports bb.roborally.data.messages.map;
    exports bb.roborally.server;
    opens bb.roborally.gui to javafx.fxml;
    exports bb.roborally.game;
    exports bb.roborally.gui.data;
    opens bb.roborally.gui.data to javafx.fxml;
}