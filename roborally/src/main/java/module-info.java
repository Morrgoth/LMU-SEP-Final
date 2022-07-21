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
    exports bb.roborally.server;
    exports bb.roborally.server.game.tiles;
    exports bb.roborally.server.game.cards;
    exports bb.roborally.server.game.board;
    exports bb.roborally.protocol.type_adapters.map;
    opens bb.roborally.client to javafx.fxml;
    exports bb.roborally.server.game;
    exports bb.roborally.server.game.activation;
    exports bb.roborally.server.game.map;
    exports bb.roborally.server.game.deck;
    exports bb.roborally.client.programming_interface;
    opens bb.roborally.client.programming_interface to javafx.fxml;
    exports bb.roborally.client.player_list;
    opens bb.roborally.client.player_list to javafx.fxml;
    exports bb.roborally.client.robot_selector;
    opens bb.roborally.client.robot_selector to javafx.fxml;
    exports bb.roborally.client.card;
    opens bb.roborally.client.card to javafx.fxml;
}