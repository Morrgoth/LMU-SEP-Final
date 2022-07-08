module roborally.roborally {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.logging;

    exports bb.roborally.client;
    exports bb.roborally.protocol;
    exports bb.roborally.protocol.connection;
    exports bb.roborally.protocol.chat;
    exports bb.roborally.protocol.game_events;
    exports bb.roborally.protocol.gameplay;
    exports bb.roborally.protocol.lobby;
    exports bb.roborally.protocol.map;
    //exports bb.roborally.data.util;
    exports bb.roborally.server;
    exports bb.roborally.server.game.tiles;
    exports bb.roborally.server.game.cards;
    exports bb.roborally.server.game.board;
    exports bb.roborally.protocol.type_adapters.map;
    opens bb.roborally.client to javafx.fxml;
    exports bb.roborally.server.game;
    exports bb.roborally.client.data;
    opens bb.roborally.client.data to javafx.fxml;
}