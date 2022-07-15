package bb.roborally.protocol.type_adapters.map;

import bb.roborally.server.game.board.Board;
import bb.roborally.server.game.board.Cell;
import bb.roborally.server.game.tiles.Tile;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Muqiu Wang
 * @author Philipp Keyzman
 * @author Veronika Heckel
 */
public class BoardTypeAdapter extends TypeAdapter<Board> {
    @Override
    public void write(JsonWriter jsonWriter, Board board) throws IOException {
        TileTypeAdapter tileTypeAdapter = new TileTypeAdapter();
        jsonWriter.beginObject();
        jsonWriter.name("gameMap");
        jsonWriter.beginArray();
        for (ArrayList<Cell> xAndy : board.getGameMap()) {
            jsonWriter.beginArray();
            for (Cell cell : xAndy) {
                jsonWriter.beginArray();
                for (Tile tile : cell.getTiles()) {
                    tileTypeAdapter.write(jsonWriter, tile);
                }
                jsonWriter.endArray();
            }
            jsonWriter.endArray();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public Board read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            ArrayList<ArrayList<Cell>> map = null;
            if (name.equals("gameMap")) {
                map = new ArrayList<>();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    ArrayList<Cell> xAndy = new ArrayList<>();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        ArrayList<Tile> field = new ArrayList<>();
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            Tile tile = new TileTypeAdapter().read(jsonReader);
                            field.add(tile);
                        }
                        jsonReader.endArray();
                        xAndy.add(new Cell(field));
                    }
                    jsonReader.endArray();
                    map.add(xAndy);
                }
                jsonReader.endArray();
                Board board = new Board(map);
                return board;
            }
        }
        jsonReader.endObject();
        return null;
    }
}


