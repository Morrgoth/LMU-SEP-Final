package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.tiles.*;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TileTypeAdaptor extends TypeAdapter<Tile> {
    @Override
    public void write(JsonWriter jsonWriter, Tile tile) throws IOException {
        jsonWriter.beginObject();
        if(tile instanceof ConveyorBelt){
            jsonWriter.name("type").value(((ConveyorBelt) tile).getName());
        }else if(tile instanceof Wall){
            jsonWriter.name("type").value(((Wall) tile).getName());
        }else if(tile instanceof Antenna){
            jsonWriter.name("type").value(((Antenna) tile).getName());
        }else if(tile instanceof BlackHole){
            jsonWriter.name("type").value(((BlackHole) tile).getName());
        }
        jsonWriter.name("type").value(tile.getType());
        jsonWriter.endObject();
    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
