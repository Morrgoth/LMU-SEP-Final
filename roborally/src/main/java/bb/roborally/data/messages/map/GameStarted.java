package bb.roborally.data.messages.map;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.Board;
import bb.roborally.game.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class GameStarted implements Message {
    private ArrayList<ArrayList<ArrayList<ArrayList<Tile>>>> gameMap;

    public ArrayList<ArrayList<ArrayList<ArrayList<Tile>>>> getGameMap() {
        return gameMap;
    }

    public void setGameMap(ArrayList<ArrayList<ArrayList<ArrayList<Tile>>>> gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public String toJson() {
        return toEnvelope().toJson();
    }

    @Override
    public Envelope toEnvelope() {
        return new Envelope(Envelope.MessageType.GAME_STARTED, this);
    }
}
