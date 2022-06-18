package bb.roborally.game;


import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.Message;

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
    private ArrayList<Cell> board;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Cell> board) {
        this.board = board;
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
