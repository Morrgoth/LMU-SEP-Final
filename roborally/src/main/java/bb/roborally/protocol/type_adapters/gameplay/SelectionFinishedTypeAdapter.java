package bb.roborally.protocol.type_adapters.gameplay;

import bb.roborally.protocol.gameplay.SelectionFinished;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SelectionFinishedTypeAdapter extends TypeAdapter<SelectionFinished> {
    @Override
    public void write(JsonWriter jsonWriter, SelectionFinished selectionFinished) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(selectionFinished.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public SelectionFinished read(JsonReader jsonReader) throws IOException {
        SelectionFinished selectionFinished = new SelectionFinished();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("clientID")) {
                selectionFinished.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return selectionFinished;
    }
}
