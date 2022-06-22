package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.Board;
import bb.roborally.game.tiles.Tile;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

public class BoardTypeAdapter extends TypeAdapter<Board> {
    @Override
    public void write(JsonWriter jsonWriter, Board board) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("gameMap");
        jsonWriter.beginArray();
        //Map eintragen

        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public Board read(JsonReader jsonReader) throws IOException {

        return null;
    }
}
