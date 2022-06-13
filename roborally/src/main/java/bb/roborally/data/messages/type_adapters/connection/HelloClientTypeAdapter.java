package bb.roborally.data.messages.type_adapters.connection;

import bb.roborally.data.messages.connection.HelloClient;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class HelloClientTypeAdapter extends TypeAdapter<HelloClient> {

    @Override
    public void write(JsonWriter jsonWriter, HelloClient helloClient) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("protocol").value(helloClient.getProtocol());
        jsonWriter.endObject();
    }

    @Override
    public HelloClient read(JsonReader jsonReader) throws IOException {
        HelloClient helloClient = new HelloClient();
        jsonReader.beginObject();
        String name;
        while (jsonReader.hasNext()) {
            name = jsonReader.nextName();
            if (name.equals("protocol")) {
                helloClient.setProtocol(jsonReader.nextString());
            }

        }
        jsonReader.endObject();
        return helloClient;
    }
}