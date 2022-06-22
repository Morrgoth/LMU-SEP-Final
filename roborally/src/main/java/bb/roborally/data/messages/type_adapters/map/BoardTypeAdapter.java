package bb.roborally.data.messages.type_adapters.map;

import bb.roborally.game.PlayingField;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BoardTypeAdapter extends TypeAdapter<PlayingField> {
    @Override
    public void write(JsonWriter jsonWriter, PlayingField board) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("gameMap");
        jsonWriter.beginArray();
        //Map eintragen

        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public PlayingField read(JsonReader jsonReader) throws IOException {

        return null;
    }
}
