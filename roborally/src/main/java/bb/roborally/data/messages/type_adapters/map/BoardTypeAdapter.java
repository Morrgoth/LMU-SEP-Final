package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.Tile;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BoardTypeAdapter extends TypeAdapter<Board> {
    @Override
    public void write(JsonWriter jsonWriter, Board board) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("gameMap");
        jsonWriter.beginArray();
        for(ArrayList<ArrayList<Tile>> xAndy: board.getGameMap()){
            jsonWriter.value(String.valueOf(xAndy));
            jsonWriter.beginArray();
            for(ArrayList<Tile> cell: xAndy){
                jsonWriter.value(String.valueOf(cell));
                jsonWriter.beginArray();
                for(Tile tile: cell){
                    jsonWriter.value(String.valueOf(tile));
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
        ArrayList<ArrayList<ArrayList<Tile>>> map = new ArrayList<>();
        if(jsonReader.hasNext()){
            if(jsonReader.nextName().equals("gameMap")){
                jsonReader.beginArray();
                //set values
                jsonReader.endArray();
            }
        }
        Board board = new Board(map);
        jsonReader.endObject();
        return board;
    }
}
