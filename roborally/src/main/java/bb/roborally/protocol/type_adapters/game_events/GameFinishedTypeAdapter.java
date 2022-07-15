package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.GameFinished;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GameFinishedTypeAdapter extends TypeAdapter<GameFinished> {
    @Override
    public void write(JsonWriter jsonWriter, GameFinished gameFinished) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(gameFinished.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public GameFinished read(JsonReader jsonReader) throws IOException {
        GameFinished gameFinished = new GameFinished();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("clientID")){
                gameFinished.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return gameFinished;
    }
}
