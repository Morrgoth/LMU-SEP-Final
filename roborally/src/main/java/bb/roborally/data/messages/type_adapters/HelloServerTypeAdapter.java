package bb.roborally.data.messages.type_adapters;

import bb.roborally.data.messages.connection.HelloServer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class HelloServerTypeAdapter extends TypeAdapter<HelloServer> {

    @Override
    public void write(JsonWriter jsonWriter, HelloServer helloServer) throws IOException {
        jsonWriter.beginObject();
            jsonWriter.name("group").value(helloServer.getGroup());
            jsonWriter.name("isAI").value(helloServer.isAI());
            jsonWriter.name("protocol").value(helloServer.getProtocol());
        jsonWriter.endObject();
    }

    @Override
    public HelloServer read(JsonReader jsonReader) throws IOException {
        HelloServer helloServer = new HelloServer();
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            if(jsonReader.nextName().equals("protocol")){
                helloServer.setProtocol(jsonReader.nextString());
            }
            if(jsonReader.nextName().equals("group")){
                helloServer.setGroup(jsonReader.nextString());
            }
            if(jsonReader.nextName().equals("isAI")){
                helloServer.setAI(jsonReader.nextBoolean());
            }
            //ConnectionType - Enums müssen noch in die Envelope (Message-Type)
        }
        jsonReader.endObject();
        return null;
    }
}
