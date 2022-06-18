package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.data.messages.game_events.CheckPointReached;
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
        }else if(tile instanceof CheckPoint){
            jsonWriter.name("type").value(((CheckPoint) tile).getName());
        }else if(tile instanceof EnergySpace){
            jsonWriter.name("type").value(((EnergySpace) tile).getName());
        }else if(tile instanceof Floor){
            jsonWriter.name("type").value(((Floor) tile).getName());
        }else if(tile instanceof Gear){
            jsonWriter.name("type").value(((Gear) tile).getName());
        }else if(tile instanceof Laser){
            jsonWriter.name("type").value(((Laser) tile).getName());
        }else if(tile instanceof PushPanel){
            jsonWriter.name("type").value(((PushPanel) tile).getName());
        }else if(tile instanceof RebootPoint){
            jsonWriter.name("type").value(((RebootPoint) tile).getName());
        }else if(tile instanceof StartPoint){
            jsonWriter.name("type").value(((StartPoint) tile).getName());
        }

        jsonWriter.endObject();
    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
