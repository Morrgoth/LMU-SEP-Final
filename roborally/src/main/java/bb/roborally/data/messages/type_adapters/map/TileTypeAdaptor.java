package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.data.messages.Envelope;
import bb.roborally.data.messages.game_events.CheckPointReached;
import bb.roborally.data.messages.type_adapters.*;
import bb.roborally.data.messages.type_adapters.chat.ReceivedChatTypeAdapter;
import bb.roborally.data.messages.type_adapters.chat.SendChatTypeAdapter;
import bb.roborally.data.messages.type_adapters.connection.AliveTypeAdapter;
import bb.roborally.data.messages.type_adapters.connection.HelloClientTypeAdapter;
import bb.roborally.data.messages.type_adapters.connection.HelloServerTypeAdapter;
import bb.roborally.data.messages.type_adapters.connection.WelcomeTypeAdapter;
import bb.roborally.data.messages.type_adapters.game_events.*;
import bb.roborally.data.messages.type_adapters.gameplay.*;
import bb.roborally.data.messages.type_adapters.lobby.PlayerAddedTypeAdapter;
import bb.roborally.data.messages.type_adapters.lobby.PlayerStatusTypeAdapter;
import bb.roborally.data.messages.type_adapters.lobby.PlayerValuesTypeAdapter;
import bb.roborally.data.messages.type_adapters.lobby.SetStatusTypeAdapter;
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
        }else if(tile instanceof Wall){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof Antenna){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof BlackHole){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof CheckPoint){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof EnergySpace){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof Floor){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof Gear){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof Laser){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof PushPanel){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof RebootPoint){
            jsonWriter.name("type").value(tile.getType());
        }else if(tile instanceof StartPoint){
            jsonWriter.name("type").value(tile.getType());
        }

        jsonWriter.endObject();
    }

    @Override
    public Tile read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
