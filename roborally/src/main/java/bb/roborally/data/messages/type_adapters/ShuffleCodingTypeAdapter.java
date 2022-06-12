package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.gameplay.ShuffleCoding;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Veronika Heckel
 */
public class ShuffleCodingTypeAdapter extends TypeAdapter<ShuffleCoding> {
    @Override
    public void write(JsonWriter jsonWriter, ShuffleCoding shuffleCoding) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(shuffleCoding.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public ShuffleCoding read(JsonReader jsonReader) throws IOException {
        ShuffleCoding shuffleCoding = new ShuffleCoding();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()){
            name = jsonReader.nextName();
            if(name.equals("clientID")){
                shuffleCoding.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return shuffleCoding;
    }
}
