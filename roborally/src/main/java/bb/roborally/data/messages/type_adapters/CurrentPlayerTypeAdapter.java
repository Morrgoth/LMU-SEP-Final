package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.CurrentPlayer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CurrentPlayerTypeAdapter extends TypeAdapter<CurrentPlayer> {
    @Override
    public void write(JsonWriter jsonWriter, CurrentPlayer currentPlayer) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(currentPlayer.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public CurrentPlayer read(JsonReader jsonReader) throws IOException {
        CurrentPlayer currentPlayer = new CurrentPlayer();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if (name.equals("clientID")) {
                currentPlayer.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return currentPlayer;
    }
}
