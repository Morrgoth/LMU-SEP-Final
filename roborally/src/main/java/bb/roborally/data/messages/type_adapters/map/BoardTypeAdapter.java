package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.board.Board;
import bb.roborally.game.tiles.Tile;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;


public class BoardTypeAdapter extends TypeAdapter<Board> {
    @Override
    public void write(JsonWriter jsonWriter, Board board) throws IOException {
        TileTypeAdapter tileTypeAdapter = new TileTypeAdapter();
        jsonWriter.beginObject();
        jsonWriter.name("gameMap");
        jsonWriter.beginArray();
        for(ArrayList<ArrayList<Tile>> xAndy: board.getGameMap()){
            jsonWriter.beginArray();
            for(ArrayList<Tile> cell: xAndy){
                jsonWriter.beginArray();
                for(Tile tile: cell){
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
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("gameMap")){
                ArrayList<ArrayList<ArrayList<Tile>>> map = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    ArrayList<ArrayList<Tile>> xAndy = new ArrayList<>();
                    jsonReader.beginArray();
                    while(jsonReader.hasNext()){
                        ArrayList<Tile> field = new ArrayList<>();
                        jsonReader.beginArray();
                        while(jsonReader.hasNext()){
                            Tile tile = new TileTypeAdapter().read(jsonReader);
                            field.add(tile);
                        }
                        jsonReader.endArray();
                        xAndy.add(field);
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
