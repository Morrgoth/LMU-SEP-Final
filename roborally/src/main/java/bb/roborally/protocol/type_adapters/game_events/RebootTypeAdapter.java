package bb.roborally.protocol.type_adapters.game_events;

import bb.roborally.protocol.game_events.Reboot;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class RebootTypeAdapter extends TypeAdapter<Reboot> {
    @Override
    public void write(JsonWriter jsonWriter, Reboot reboot) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("clientID").value(reboot.getClientID());
        jsonWriter.endObject();
    }

    @Override
    public Reboot read(JsonReader jsonReader) throws IOException {
        Reboot reboot = new Reboot();
        jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            if(name.equals("clientID")){
                reboot.setClientID(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        return reboot;
    }
}
