package bb.roborally.game;


import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;
import bb.roborally.game.tiles.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * this class creates the different Boards of the Game
 * @author Veronika Heckel
 * @author Muqiu Wang
 * @author Tolga Engin
 * @author Zeynab Baiani
 * @author Bence Ament
 * @autor  Philipp Keyzman
 */
public class Board implements Message {
    private String name;
    private ArrayList<ArrayList<ArrayList<ArrayList<Tile>>>> gameMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
