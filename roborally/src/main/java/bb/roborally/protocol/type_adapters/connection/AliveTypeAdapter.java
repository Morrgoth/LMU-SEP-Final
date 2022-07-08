package bb.roborally.protocol.type_adapters.connection;

import bb.roborally.protocol.connection.Alive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class AliveTypeAdapter extends TypeAdapter<Alive> {

    @Override
    public void write(JsonWriter jsonWriter, Alive alive) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.endObject();
    }

    @Override
    public Alive read(JsonReader jsonReader) throws IOException {
        Alive alive = new Alive();
        jsonReader.beginObject();
        while (jsonReader.hasNext()){

        }
        jsonReader.endObject();
        return alive;
    }
}
