package bb.roborally.protocol.type_adapters;

import bb.roborally.server.game.tiles.*;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TileTypeAdapter extends TypeAdapter<Tile> {
    @Override
    public void write(JsonWriter jsonWriter, Tile tile) throws IOException {
        if (tile instanceof Antenna){

        }
        else if (tile instanceof ConveyorBelt){

        }
        else if (tile instanceof Pit){

        }
        else if (tile instanceof CheckPoint){

        }
        else if (tile instanceof EnergySpace){

        }
        else if (tile instanceof Empty){

        }
        else if (tile instanceof Gear){

        }
        else if (tile instanceof Laser){

        }
        else if (tile instanceof PushPanel){

        }
        else if (tile instanceof StartPoint){

        }
        else if (tile instanceof Tile){

        }
        else if (tile instanceof Wall){

        }

    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
