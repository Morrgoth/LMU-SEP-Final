package bb.roborally.data.messages.type_adapters.game_events;

import bb.roborally.data.messages.game_events.PlayerTurning;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class PlayerTurningTypeAdapter extends TypeAdapter<PlayerTurning> {

    @Override
    public void write(JsonWriter jsonWriter, PlayerTurning playerTurning) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(playerTurning.getClientID());
        jsonWriter.name("rotation").value(playerTurning.getRotation());
        jsonWriter.endObject();
    }

    @Override
    public PlayerTurning read(JsonReader jsonReader) throws IOException {
        PlayerTurning playerTurning = new PlayerTurning();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("clientID")){
                playerTurning.setClientID(jsonReader.nextInt());
            }
            if(name.equals("rotation")){
                String rotation = jsonReader.nextString();
                playerTurning.setRotation(rotation);
            }
        }
        jsonReader.endObject();
        return playerTurning;
    }
}
