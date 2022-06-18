package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.Orientation;
import bb.roborally.game.tiles.*;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileTypeAdaptor extends TypeAdapter<Tile> {
    @Override
    public void write(JsonWriter jsonWriter, Tile tile) throws IOException {
        jsonWriter.beginObject();
        if(tile instanceof ConveyorBelt){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("speed").value(((ConveyorBelt) tile).getSpeed());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
        }else if(tile instanceof PushPanel){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
            jsonWriter.name("registers");
            jsonWriter.beginArray();
            for(Integer r: ((PushPanel) tile).getRegisters())
                jsonWriter.value(r);
            jsonWriter.endArray();
        }else if(tile instanceof Laser){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
            jsonWriter.name("count").value(((Laser) tile).getCount());
        }else if(tile instanceof CheckPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
            jsonWriter.name("number").value(((CheckPoint) tile).getNumber());
        }else if(tile instanceof EnergySpace){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
            jsonWriter.name("remainedEnergyCube").value(((EnergySpace) tile).getRemainedEnergyCube());
        }else if(tile instanceof Gear){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
            jsonWriter.name("direction").value(((Gear) tile).getDirection());
        }else if(tile instanceof Wall || tile instanceof Antenna || tile instanceof BlackHole || tile instanceof Floor ||
                tile instanceof RebootPoint || tile instanceof StartPoint){
            jsonWriter.name("type").value(tile.getType());
            jsonWriter.name("isOnBoard").value(tile.getIsOnBoard());
            jsonWriter.name("orientations");
            jsonWriter.beginArray();
            for(String o: tile.getOrientations())
                jsonWriter.value(o);
            jsonWriter.endArray();
        }
        jsonWriter.endObject();
    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
